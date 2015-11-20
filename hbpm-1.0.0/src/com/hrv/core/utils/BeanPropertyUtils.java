package com.hrv.core.utils;

public class BeanPropertyUtils {
	private static final String GET_STACK_TRACE = "getStackTrace";
	private static final String UNKNOWN_METHOD = "<unknown method>";

	public static final String getMethodName(StackTraceElement e[]) {
		String temp = null;

		for (StackTraceElement s : e) {
			temp = s.getMethodName();

			if (!temp.equals(GET_STACK_TRACE)) {
				return temp;
			}
		}

		return UNKNOWN_METHOD;
	}
}