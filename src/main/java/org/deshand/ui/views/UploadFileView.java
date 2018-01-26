package org.deshand.ui.views;

import java.io.OutputStream;

import org.deshand.excel.ApachePOIExcelRead;
import org.deshand.ui.desings.UploadFileDesing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Window;

@Secured("ROLE_ADMIN")
@SpringView(name = UploadFileView.NAME)
public class UploadFileView extends UploadFileDesing implements View {
	
	@Autowired
	private ApachePOIExcelRead reader;

	public static final String NAME = "upload";

	private static final long serialVersionUID = -6231029225165237125L;

	public UploadFileView() {
		super();

		LineBreakCounter lineBreakCounter = new LineBreakCounter();
		lineBreakCounter.setSlow(true);

		this.upload.setReceiver(lineBreakCounter);

		this.upload.setImmediateMode(false);
		this.upload.setButtonCaption("Обновить базу данных ");

		UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(upload, lineBreakCounter);

		this.upload.addStartedListener(event -> {
			if (uploadInfoWindow.getParent() == null) {
				UI.getCurrent().addWindow(uploadInfoWindow);
			}
			uploadInfoWindow.setClosable(false);
		});
		this.upload.addFinishedListener(event -> uploadInfoWindow.setClosable(true));

	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@StyleSheet("uploadexample.css")
	private static class UploadInfoWindow extends Window implements Upload.StartedListener, Upload.ProgressListener,
			Upload.FailedListener, Upload.SucceededListener, Upload.FinishedListener {
		private final Label state = new Label();
		private final Label result = new Label();
		private final Label fileName = new Label();
		private final Label textualProgress = new Label();

		private final ProgressBar progressBar = new ProgressBar();
		private final Button cancelButton;
		private final LineBreakCounter counter;

		private UploadInfoWindow(final Upload upload, final LineBreakCounter lineBreakCounter) {
			super("Status");
			this.counter = lineBreakCounter;

			addStyleName("upload-info");

			setResizable(false);
			setDraggable(false);

			final FormLayout uploadInfoLayout = new FormLayout();
			setContent(uploadInfoLayout);
			uploadInfoLayout.setMargin(true);

			final HorizontalLayout stateLayout = new HorizontalLayout();
			stateLayout.setSpacing(true);
			stateLayout.addComponent(state);

			cancelButton = new Button("Cancel");
			cancelButton.addClickListener(event -> upload.interruptUpload());
			cancelButton.setVisible(false);
			cancelButton.setStyleName("small");
			stateLayout.addComponent(cancelButton);

			stateLayout.setCaption("Current state");
			state.setValue("Idle");
			uploadInfoLayout.addComponent(stateLayout);

			fileName.setCaption("File name");
			uploadInfoLayout.addComponent(fileName);

			result.setCaption("Line breaks counted");
			uploadInfoLayout.addComponent(result);

			progressBar.setCaption("Progress");
			progressBar.setVisible(false);
			uploadInfoLayout.addComponent(progressBar);

			textualProgress.setVisible(false);
			uploadInfoLayout.addComponent(textualProgress);

			upload.addStartedListener(this);
			upload.addProgressListener(this);
			upload.addFailedListener(this);
			upload.addSucceededListener(this);
			upload.addFinishedListener(this);

		}

		@Override
		public void uploadFinished(final FinishedEvent event) {
			state.setValue("Idle");
			progressBar.setVisible(false);
			textualProgress.setVisible(false);
			cancelButton.setVisible(false);
		}

		@Override
		public void uploadStarted(final StartedEvent event) {
			// this method gets called immediately after upload is started
			progressBar.setValue(0f);
			progressBar.setVisible(true);
			UI.getCurrent().setPollInterval(500);
			textualProgress.setVisible(true);
			// updates to client
			state.setValue("Uploading");
			fileName.setValue(event.getFilename());

			cancelButton.setVisible(true);
		}

		@Override
		public void updateProgress(final long readBytes, final long contentLength) {
			// this method gets called several times during the update
			progressBar.setValue(readBytes / (float) contentLength);
			textualProgress.setValue("Processed " + readBytes + " bytes of " + contentLength);
			result.setValue(counter.getLineBreakCount() + " (counting...)");
		}

		@Override
		public void uploadSucceeded(final SucceededEvent event) {
			result.setValue(counter.getLineBreakCount() + " (total)");
		}

		@Override
		public void uploadFailed(final FailedEvent event) {
			result.setValue(counter.getLineBreakCount() + " (counting interrupted at "
					+ Math.round(100 * progressBar.getValue()) + "%)");
		}
	}

	private static class LineBreakCounter implements Receiver {
		private int counter;
		private int total;
		private boolean sleep;

		/**
		 * return an OutputStream that simply counts lineends
		 */
		@Override
		public OutputStream receiveUpload(final String filename, final String MIMEType) {
			
			System.out.println(filename);
			counter = 0;
			total = 0;
			return new OutputStream() {
				private static final int searchedByte = '\n';

				@Override
				public void write(final int b) {
					total++;
					if (b == searchedByte) {
						counter++;
					}
					if (sleep && total % 1000 == 0) {
						try {
							Thread.sleep(100);
						} catch (final InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
		}

		private int getLineBreakCount() {
			return counter;
		}

		private void setSlow(boolean value) {
			sleep = value;
		}
	}

	private static class ExcelFileProcessor implements Receiver {
		private int counter;
		private int total;
		private boolean sleep;

		// Here I'm going to process an excel file which I didn't poses yet.
		@Override
		public OutputStream receiveUpload(final String filename, final String MIMEType) {
			counter = 0;
			total = 0;
			return new OutputStream() {
				private static final int searchedByte = '\n';

				@Override
				public void write(final int b) {
					total++;
					if (b == searchedByte) {
						counter++;
					}
					if (sleep && total % 1000 == 0) {
						try {
							Thread.sleep(100);
						} catch (final InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
		}

		private int getLineBreakCount() {
			return counter;
		}

		private void setSlow(boolean value) {
			sleep = value;
		}
	}

}
