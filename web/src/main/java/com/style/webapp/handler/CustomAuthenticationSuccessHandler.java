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
		String responseURL = "/";

		if (roles.contains("ROLE_ADMIN")) {
			responseURL = "/admin";
			//response.sendRedirect(response.encodeURL("/admin"));
		} else if (roles.contains("ROLE_BRANCH_ADMIN")) {
			responseURL = "/branch";
			//response.sendRedirect(response.encodeURL("/branch"));
		}/* else if (roles.contains("ROLE_USER")) {
			responseURL = "/";
			//response.sendRedirect(response.encodeURL("/"));
		}*/

		if ("true".equals(request.getHeader("X-Ajax-call"))) {
			try {
				response.getWriter().print(
						"status : success, redirectUrl : " + responseURL);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(response.encodeURL(responseURL));
		}
	}
}