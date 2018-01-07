package org.deshand.ui.components;

import org.deshand.ui.views.MainView;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Link;

public class GoToMainViewLink extends CustomComponent {

	private static final long serialVersionUID = -2575325139795360994L;

	public GoToMainViewLink() {
		Link goBackLink = new Link("Go back to main", new ExternalResource("#!" + MainView.NAME));
		goBackLink.setIcon(VaadinIcons.HOME);
		setCompositionRoot(goBackLink);
	}
}
