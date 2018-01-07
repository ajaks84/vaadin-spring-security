package org.deshand.ui.views;

import org.deshand.ui.MainUI;
import org.deshand.ui.events.LogoutEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = LogoutView.NAME)
public class LogoutView extends Navigator.EmptyView {

	private static final long serialVersionUID = -3127171386195613305L;
	
	public static final String NAME = "logout";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        MainUI.getCurrent().getEventbus().post(new LogoutEvent(this));
    }
}
