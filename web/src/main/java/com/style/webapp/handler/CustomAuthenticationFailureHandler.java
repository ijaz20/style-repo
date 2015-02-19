package com.style.webapp.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

public class CustomAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException authenticationException) throws IOException,
                    ServletException {
    	String errorMessage = "";
        if (authenticationException instanceof BadCredentialsException) {
            errorMessage =  "Username or password is wrong";
        } else if (authenticationException instanceof DisabledException) {
            errorMessage = "your account disabled";
        } else {
            errorMessage = authenticationException.getMessage();
        }
        
        if ("true".equals(request.getHeader("X-Ajax-call"))) {
			try {
				response.getWriter().print(
						"status : error, errorMessage : " + errorMessage);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login?error=true");
		}
        
    }
}
