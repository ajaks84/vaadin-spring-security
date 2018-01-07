package org.deshand.ui.components;

import org.deshand.ui.MainUI;
import org.deshand.ui.views.LogoutView;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Link;

public class LogoutLink extends CustomComponent {

	private static final long serialVersionUID = 5403489200089169664L;

	public LogoutLink() {
		Link logoutLink = new Link("Logout", new ExternalResource("#!" + LogoutView.NAME));
		logoutLink.setIcon(VaadinIcons.SIGN_OUT);
		setCompositionRoot(logoutLink);
	}

	public void updateVisibility() {
		setVisible(!MainUI.getCurrent().isUserAnonymous());
		System.out.println("user is anonymous: "+MainUI.getCurrent().isUserAnonymous());
		
	}
}
