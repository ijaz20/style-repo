package com.style.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.style.Constants;

public final class CommonUtil {

	/**
	 * check the user is anonymous
	 * 
	 * @return
	 */
	public static boolean isAnonymous() {
		AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			return resolver.isAnonymous(auth);
		}
		return true;
	}

	/**
	 * get current session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * get current request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest();
	}

	/**
	 * get servlet context
	 * 
	 * @return
	 */
	public static ServletContext getServletcontext() {
		return getSession().getServletContext();
	}

	/**
	 * get browserId to find unique user
	 * 
	 * @return
	 */
	public static String getBrowserId() {
		return (String) getServletcontext().getAttribute(Constants.BROWSER_ID);
	}
}
