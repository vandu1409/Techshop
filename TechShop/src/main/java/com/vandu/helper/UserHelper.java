package com.vandu.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vandu.model.User;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserHelper {

	private static final String LOGGED_USER_SESSION_KEY = "loggedUser";

	public static User getCurrentUser(HttpServletRequest request, UserService userService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {

			User loggedUser = getLoggedUserFromSession(request);

			if (loggedUser != null) {
				User user = userService.findByUsernameAndAuthenticationType(loggedUser.getUsername(),
						loggedUser.getAuthenticationType()).orElse(null);
				if (user != null) {
					return user;
				}
			}

		}

		return null;
	}

	public static User getLoggedUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User) session.getAttribute(LOGGED_USER_SESSION_KEY);
	}
}
