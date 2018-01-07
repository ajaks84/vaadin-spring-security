package org.deshand.ui.views;

import org.deshand.ui.MainUI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "")
public class RedirectToMainView extends Navigator.EmptyView {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8623749685494718114L;

	@Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        MainUI.getCurrent().getNavigator().navigateTo(MainView.NAME);
    }
}
