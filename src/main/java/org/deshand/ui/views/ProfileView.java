package org.deshand.ui.views;

import org.deshand.ui.desings.ProfileDesing;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

@Secured("ROLE_USER")
@SpringView(name = ProfileView.NAME)
public class ProfileView extends ProfileDesing implements View{

	private static final long serialVersionUID = -5763738980485188530L;
	
	public static final String NAME = "profile";

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	
}
