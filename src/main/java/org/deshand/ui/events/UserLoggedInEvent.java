package org.deshand.ui.events;

import com.google.common.base.Preconditions;
import java.util.EventObject;
import org.deshand.model.User;

public class UserLoggedInEvent extends EventObject {

	private static final long serialVersionUID = 4225751278067837728L;
	private User user;

	public UserLoggedInEvent(Object source, User user) {
		super(source);
		Preconditions.checkNotNull(user);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
