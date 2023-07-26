package com.vandu.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.vandu.enums.AuthenticationType;
import com.vandu.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.err.println("AU "+authentication.getName());
		User user = new User();
		user.setUsername(authentication.getName());
		user.setAuthenticationType(AuthenticationType.LOCAL);
		
		System.err.println(user);
		
		request.getSession().setAttribute("loggedUser", user);
		
		System.err.println("SSSSSSSSS"+request.getAttribute("loggedUser"));
		 response.sendRedirect("/home");
		
	}

}
