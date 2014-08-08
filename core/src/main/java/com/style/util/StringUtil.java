package com.style.util;

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
}