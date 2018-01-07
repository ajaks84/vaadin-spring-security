package org.deshand.ui.views;

import org.deshand.ui.components.GoToMainViewLink;
import org.deshand.ui.components.LogoutLink;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.navigator.ViewChangeListener;
//import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;

@Secured("ROLE_ADMIN")
@SpringView(name = AdminView.NAME)
public class AdminView extends AbstractView {

	private static final long serialVersionUID = -6353958655378478034L;
	
	public static final String NAME = "admin";
	
	private LogoutLink logoutLink;

	public AdminView() {

		this.logoutLink = new LogoutLink();

		this.logoutLink.updateVisibility();
		
		HorizontalLayout navigator = new HorizontalLayout(new GoToMainViewLink(),logoutLink);
		setContent(navigator);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
