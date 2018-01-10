package org.deshand.ui.views;

import org.deshand.model.User;
import org.deshand.service.AdminService;
import org.deshand.ui.MainUI;
import org.deshand.ui.components.LogoutLink;
import org.deshand.ui.events.LogoutEvent;
import org.deshand.ui.events.UserLoggedInEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.google.common.eventbus.Subscribe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

@SpringView(name = MainView.NAME)
public class MainView extends AbstractView {

	private static final long serialVersionUID = 7942074244486700158L;

	@Autowired
	private AdminService adminService;

	public final static String NAME = "main";

	private LogoutLink logoutLink;

	private Label welcomeLabelText;

	public MainView() {

		welcomeLabelText = new Label("", ContentMode.HTML);
		updateWelcomeMessage();
		addComponent(welcomeLabelText);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		
		final Link wareHouseLink = new Link("Central Warehouse", new ExternalResource("#!" + WareHouseView.NAME));
		wareHouseLink.setIcon(VaadinIcons.CUBES);
		horizontalLayout.addComponent(wareHouseLink);
		
		final Link profileLink = new Link("Your Profile", new ExternalResource("#!" + ProfileView.NAME));
		profileLink.setIcon(VaadinIcons.USER);
		horizontalLayout.addComponent(profileLink);
		
		//Example of "Page not found" can be added to main view here.
		final Link invalidLink = new Link("Go to some invalid page", new ExternalResource("#!invalid_page"));
		invalidLink.setIcon(VaadinIcons.BOMB);
//		horizontalLayout.addComponent(invalidLink);

		logoutLink = new LogoutLink();
		logoutLink.updateVisibility();
		horizontalLayout.addComponent(logoutLink);

		Link adminLink = new Link("Admin page", new ExternalResource("#!" + AdminView.NAME));
		adminLink.setIcon(VaadinIcons.LOCK);
		horizontalLayout.addComponent(adminLink);

		Link aboutLink = new Link("About", new ExternalResource("#!" + AboutView.NAME));
		aboutLink.setIcon(VaadinIcons.QUESTION_CIRCLE);
		horizontalLayout.addComponent(aboutLink);
		addComponent(horizontalLayout);

		Button adminButton = new Button("Admin Button");
		adminButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 5499056527435374608L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				adminService.doSomeAdministrationTask();
			}
		});

		// Here is a button added to a view
		// addComponent(adminButton);

		registerWithEventbus();
	}

	private void updateWelcomeMessage() {
		String username = null;
		if (!MainUI.getCurrent().isUserAnonymous()) {
			final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			username = principal.getFullName();
		}
		welcomeLabelText
				.setValue(username == null ? "<h1>Welcome Stranger</h1><hr/>You're currently not logged in.<hr/>"
						: "<h1>Welcome " + username + "!</h1><hr/>");

	}

	@Subscribe
	public void userLoggedIn(UserLoggedInEvent event) {
		updateWelcomeMessage();
		logoutLink.updateVisibility();
	}

	@Subscribe
	public void userLoggedOut(LogoutEvent event) {
		updateWelcomeMessage();
		logoutLink.updateVisibility();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
