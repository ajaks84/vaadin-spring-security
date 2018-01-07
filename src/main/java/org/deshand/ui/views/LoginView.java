package org.deshand.ui.views;

import org.deshand.spring.security.UserAuthenticationService;
import org.deshand.ui.MainUI;
import org.deshand.ui.components.GoToMainViewLink;
import org.deshand.ui.events.NavigationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.google.common.eventbus.EventBus;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SpringView(name = LoginView.NAME)
public class LoginView extends AbstractView implements Button.ClickListener {

	private static final long serialVersionUID = -44808860800783164L;

	public final static String NAME = "login";

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	private String forwardTo;
	private TextField nameTF;
	private PasswordField passwordTF;

	public LoginView() {
		addComponent(new Label("Please enter your credentials:"));
		nameTF = new TextField();
		nameTF.setRequiredIndicatorVisible(true);
		nameTF.setValue("user");
		nameTF.focus();

		passwordTF = new PasswordField();
		passwordTF.setRequiredIndicatorVisible(true);

		addComponent(nameTF);
		addComponent(passwordTF);

		Button loginButton = new Button("Login");
		loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		loginButton.addClickListener(this);
		loginButton.setIcon(VaadinIcons.SIGN_IN);
		addComponent(loginButton);

		addComponent(new GoToMainViewLink());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		forwardTo = event.getParameters();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// if (nameTF.is && passwordTF.isValid()) {}
		if (true) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(nameTF.getValue(),
					passwordTF.getValue());
			if (userAuthenticationService.loginUser(authentication)) {
				EventBus eventbus = MainUI.getCurrent().getEventbus();
				eventbus.post(new NavigationEvent(this, forwardTo));
			} else {
				passwordTF.setValue("");
			}
		}
		// else {
		// if (nameTF.isEmpty()) {
		// nameTF.set
//		 nameTF.setRequiredError("Please enter your username");
//		nameTF.req
		// }
		// if (passwordTF.isEmpty()) {
		// // passwordTF.setRequiredError("Please enter your password");
		// }
		// }
	}

	public static String loginPathForRequestedView(String requestedViewName) {
		return NAME + "/" + requestedViewName;
	}
}
