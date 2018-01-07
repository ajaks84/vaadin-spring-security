package org.deshand.service.impl;

import org.deshand.service.VaadinUIService;
import org.deshand.ui.MainUI;
import org.deshand.ui.events.NavigationEvent;

public class VaadinUIServiceImpl implements VaadinUIService {

    @Override
    public void postNavigationEvent(Object source, String target) {
        MainUI.getCurrent().getEventbus().post(new NavigationEvent(source, target));
    }

    @Override
    public boolean isUserAnonymous() {
        return MainUI.getCurrent().isUserAnonymous();
    }
}
