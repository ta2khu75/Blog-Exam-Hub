package com.ta2khu75.quiz.util;

public class StringUtil {
	public static String convertCamelCaseToReadable(String text) {
		// Add a space before each uppercase letter and capitalize the first letter
		if (text.endsWith("Controller")) {
			text = text.substring(0, text.length() - "Controller".length());
		}
//		String result = text.replaceAll("([A-Z])", " $1").trim();	
		String result = text.replaceAll("([a-z])([A-Z])", "$1 $2");

		// Capitalize the first letter of the result
		return result.substring(0, 1).toUpperCase() + result.substring(1);
	}
}
