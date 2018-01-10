package org.deshand.ui.views;

import org.deshand.model.User;
import org.deshand.spring.security.UserAuthenticationService;
import org.deshand.ui.MainUI;
import org.deshand.ui.desings.ProfileDesing;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

@Secured("ROLE_USER")
@SpringView(name = ProfileView.NAME)
public class ProfileView extends ProfileDesing implements View{

	private static final long serialVersionUID = -5763738980485188530L;
	
	public static final String NAME = "profile";
	
	public ProfileView(UserAuthenticationService userAuthenticationService) {
		super();
//		this.name = userAuthenticationService.loginUser(authenticationRequest)
		
		if (!MainUI.getCurrent().isUserAnonymous()) {
		final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = principal.getFullName();
		this.name.setValue(username);}
	}



	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	
}
