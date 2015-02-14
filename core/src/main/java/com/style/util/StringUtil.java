package com.style.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * String util to help string conventions
 * 
 * @author mathi
 */
public final class StringUtil {

	public StringUtil() {
	}

	/**
	 * check the string is empty
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmptyString(String s) {
		if ((s == null) || (s.trim().length() == 0)) {
			return (true);
		} else {
			return (false);
		}
	}

	/**
	 * check the object is empty
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmptyString(final Object object) {
		if ((object == null) || (object.toString().trim().length() == 0)) {
			return true;
		}
		return false;
	}
	
	/**
	 * check is email address or not
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
 	   boolean result = true;
 	   try {
 	      InternetAddress emailAddr = new InternetAddress(email);
 	      emailAddr.validate();
 	   } catch (AddressException ex) {
 	      result = false;
 	   }
 	   return result;
 	}
}