package org.deshand.ui.views;

import org.deshand.ui.components.GoToMainViewLink;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;

@SpringView(name = ErrorView.NAME)
public class ErrorView extends AbstractView {

	private static final long serialVersionUID = -696330780675714043L;

	public final static String NAME = "error";

	private String errorMessage;
	
	private String eventName;

	public ErrorView() {
		errorMessage = //"error";
				
				"<h1>Oops, page not found!</h1><hr/>"
				+ "Unfortunately, the page with name <em>"
				+ eventName
				+ "</em> is unknown to me :-( <br/>Please try something different.";
		Label errorMessageLabel = new Label(errorMessage, ContentMode.HTML);
		addComponent(errorMessageLabel);
		addComponent(new GoToMainViewLink());
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		
		System.out.println("oh shit!");
//		errorMessage = event.getViewName();
//		System.out.println(errorMessage);
		
		this.eventName=event.getViewName();
			
	}
}
