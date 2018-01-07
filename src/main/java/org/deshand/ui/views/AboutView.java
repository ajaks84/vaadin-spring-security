package org.deshand.ui.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;

@SpringView(name = AboutView.NAME)
public class AboutView extends AbstractView {

	private static final long serialVersionUID = 4921320456170817985L;
	
	public static final String NAME = "about";

    public AboutView() {
        addComponent(new Label("<h2>Приложение для просмотра наличия запчастей на центральном складе КИПиА </h2>", ContentMode.HTML));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
