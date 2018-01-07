package org.deshand.ui;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import org.deshand.model.User;
import org.deshand.service.VaadinUIService;
import org.deshand.service.impl.VaadinUIServiceImpl;
import org.deshand.ui.events.LogoutEvent;
import org.deshand.ui.events.NavigationEvent;
import org.deshand.ui.security.SecurityErrorHandler;
import org.deshand.ui.security.ViewAccessDecisionManager;
import org.deshand.ui.views.ErrorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

//@Theme("dark")
@SpringUI(path = "")
@PreserveOnRefresh
public class MainUI extends UI {

	private static final long serialVersionUID = -3534315097322509469L;

	private static final VaadinUIService uiService = new VaadinUIServiceImpl();

	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	private ViewAccessDecisionManager viewAccessDecisionManager;

	private EventBus eventbus;

	public static MainUI getCurrent() {
		return (MainUI) UI.getCurrent();
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		buildNavigator();
		VaadinSession.getCurrent().setErrorHandler(new SecurityErrorHandler(eventbus, getNavigator()));

		viewAccessDecisionManager.checkAccessRestrictionForRequestedView(getNavigator());

		Page.getCurrent().setTitle("Центральный склад КИПиА");
	}

	private void buildNavigator() {
		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(viewProvider);
		navigator.setErrorView(ErrorView.class);
		setNavigator(navigator);
	}

	public EventBus getEventbus() {
		return eventbus;
	}

	public User getCurrentUser() {
		if (isUserAnonymous()) {
			return null;
		} else {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	}

	@PostConstruct
	private void initEventbus() {
		eventbus = new EventBus("main");
		eventbus.register(this);
	}

	public boolean isUserAnonymous() {
		return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
	}

	@Subscribe
	public void userLoggedOut(LogoutEvent event) throws ServletException {
		((VaadinServletRequest) VaadinService.getCurrentRequest()).getHttpServletRequest().logout();
		System.out.println("user is auth: " + ((VaadinServletRequest) VaadinService.getCurrentRequest())
				.getHttpServletRequest().getUserPrincipal());

		VaadinSession.getCurrent().close();
		// This string of code I got from
		// https://www.programcreek.com/java-api-examples/index.php?api=com.vaadin.server.VaadinService
		// Example 6
		VaadinService.getCurrentRequest().getWrappedSession().invalidate();
		Page.getCurrent().setLocation("/");
	}

	@Subscribe
	public void handleNavigation(NavigationEvent event) {
		getNavigator().navigateTo(event.getTarget());
	}

	public static VaadinUIService getUiService() {
		return uiService;
	}
}
