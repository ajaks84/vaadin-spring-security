package org.deshand.ui.views;

import org.deshand.ui.desings.TestDesing;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = MenuView.NAME)
public class MenuView extends TestDesing implements View{

	private static final long serialVersionUID = 6902287089572482383L;
	
	public static final String NAME = "menu";

	public MenuView() {
		super();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	


}
