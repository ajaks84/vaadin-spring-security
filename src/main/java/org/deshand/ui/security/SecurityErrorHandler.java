package org.deshand.ui.security;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;

import org.deshand.ui.MainUI;
import org.deshand.ui.events.NavigationEvent;
import org.deshand.ui.views.AccessDeniedView;
import org.deshand.ui.views.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

public class SecurityErrorHandler implements ErrorHandler {

	private static final long serialVersionUID = 6279344079571816141L;
	private static Logger LOG = LoggerFactory.getLogger(SecurityErrorHandler.class);
    private EventBus eventbus;
    private Navigator navigator;

    public SecurityErrorHandler(EventBus eventbus, Navigator navigator) {
        this.eventbus = eventbus;
        this.navigator = navigator;
    }

    @Override
    public void error(ErrorEvent event) {
        LOG.error("Error handler caught exception {}", event.getThrowable().getClass().getName());
        if (event.getThrowable() instanceof AccessDeniedException || event.getThrowable().getCause() instanceof AccessDeniedException) {
            if (MainUI.getCurrent().isUserAnonymous() && !navigator.getState().startsWith(LoginView.NAME)) {
                eventbus.post(new NavigationEvent(this, LoginView.loginPathForRequestedView(navigator.getState())));
            } else if (!MainUI.getCurrent().isUserAnonymous()) {
                eventbus.post(new NavigationEvent(this, AccessDeniedView.NAME));
            }
        } else {
            // handle other exceptions a bit more graciously than this
            event.getThrowable().printStackTrace();
        }
    }
}
