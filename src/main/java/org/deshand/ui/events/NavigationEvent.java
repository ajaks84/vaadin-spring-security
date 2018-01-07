package org.deshand.ui.events;

import java.util.EventObject;

public class NavigationEvent extends EventObject {

	private static final long serialVersionUID = 1676637308233730079L;
	private String target;

	public NavigationEvent(Object source, String target) {
		super(source);
		this.target = target == null ? "" : target;
	}

	public String getTarget() {
		return target;
	}
}
