package org.deshand.ui.events;

import java.util.EventObject;

public class LogoutEvent extends EventObject {
	
	private static final long serialVersionUID = -193043994376577033L;

	public LogoutEvent(Object source) {
		super(source);
	}
}
