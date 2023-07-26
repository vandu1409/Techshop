package com.vandu.config;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginFalidHanlder implements AuthenticationFailureHandler{

//	@Autowired
//	private RedirectAttributes redirectAttributes;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		try {
			 String errorMessage = "";
			 
		        if(exception instanceof BadCredentialsException) {
		            errorMessage = "Mật khẩu hoặc tên đăng nhập không đúng!";
//		            System.err.println();
//		        	errorMessage = exception.getMessage();
		           
		        } else if(exception instanceof LockedException) {
		            errorMessage = "Tài khoản của bạn đã bị khóa ! Vui lòng thử lại!";
		        } else if(exception instanceof DisabledException) {
		            errorMessage = "Your account has been disabled. Please contact support.";
		        } else {
		            errorMessage = "Không tìm thấy tài khoản!";
		        }
		 
//		        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		        
		        System.err.println(errorMessage);
		        
//		        String redirectUrl = request.getContextPath() + "/login?error";
//	            response.sendRedirect(redirectUrl);
		        request.getSession().setAttribute("errorMessage", errorMessage);
		        response.sendRedirect(request.getContextPath() + "/login?error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
