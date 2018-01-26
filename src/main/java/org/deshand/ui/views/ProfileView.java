package org.deshand.ui.views;

import org.deshand.model.User;
import org.deshand.spring.security.UserAuthenticationService;
import org.deshand.ui.MainUI;
import org.deshand.ui.desings.ProfileDesing;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Link;

@Secured("ROLE_USER")
@SpringView(name = ProfileView.NAME)
public class ProfileView extends ProfileDesing implements View {

	private static final long serialVersionUID = -5763738980485188530L;

	public static final String NAME = "profile";

	public ProfileView(UserAuthenticationService userAuthenticationService) {
		super();

		if (!MainUI.getCurrent().isUserAnonymous()) {
			final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.name.setValue(principal.getFullName());
			this.role.setValue(principal.getAuthorities().toString());
		}
		this.link.setResource(new ExternalResource("#!" + UploadFileView.NAME));
		this.link.setIcon(VaadinIcons.UPLOAD);
		this.link.setCaption("Update Database");
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
