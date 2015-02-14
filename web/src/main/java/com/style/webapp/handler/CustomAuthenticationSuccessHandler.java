package com.style.webapp.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		/* Redirect on the successful authentication of the user */
		Collection<? extends GrantedAuthority> auths = authResult
				.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga : auths) {
			roles.add(ga.getAuthority());
		}
		if (roles.contains("ROLE_ADMIN")) {
			response.sendRedirect(response.encodeURL("/admin"));
		} else if (roles.contains("ROLE_BRANCH_ADMIN")) {
			response.sendRedirect(response.encodeURL("/branch"));
		} else if (roles.contains("ROLE_USER")) {
			response.sendRedirect(response.encodeURL("/"));
		} else {
			response.sendRedirect(response.encodeURL("/"));
		}
	}
}