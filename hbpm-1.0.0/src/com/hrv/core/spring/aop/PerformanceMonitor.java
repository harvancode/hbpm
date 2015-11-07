package com.hrv.core.spring.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PerformanceMonitor implements MethodInterceptor {
	Logger logger = LogManager.getLogger(PerformanceMonitor.class);
	private static final double MICRO = 1000;
	private static final double MILI = 1000 * 1000;
	private static final double SECOND = 1000 * 1000 * 1000;
	private static final String NANO_S = "nano";
	private static final String MICRO_S = "micro";
	private static final String MILI_S = "mili";
	private static final String SECOND_S = "second";
	private static final String NANO_DISPLAY = " ns.";
	private static final String MICRO_DISPLAY = " µs.";
	private static final String MILI_DISPLAY = " ms.";
	private static final String SECOND_DISPLAY = " s.";
	private static final String EXECUTED_IND = " executed in ";
	private String precision;

	public Object invoke(MethodInvocation method) throws Throwable {
		long start = System.nanoTime();

		try {
			return method.proceed();
		} finally {
			long end = System.nanoTime();

			if (precision != null) {
				if (NANO_S.equals(precision)) {
					printStatsInNano(method.getMethod(), end - start);
				} else if (MICRO_S.equals(precision)) {
					printStatsInMicro(method.getMethod(), end - start);
				} else if (MILI_S.equals(precision)) {
					printStatsInMili(method.getMethod(), end - start);
				} else if (SECOND_S.equals(precision)) {
					printStatsInSecond(method.getMethod(), end - start);
				}
			}
		}
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	private StringBuilder getPrefixInfo(Method method) {
		return new StringBuilder(method.getDeclaringClass().getName()).append(".").append(method.getName()).append("() ");
	}

	private void printStatsInNano(Method method, long elapsedTime) {
		logger.debug(getPrefixInfo(method).append(getTimeInNanoInfo(elapsedTime)));
	}

	private String getTimeInNanoInfo(long elapsedTime) {
		return new StringBuilder(EXECUTED_IND).append(elapsedTime).append(NANO_DISPLAY).toString();
	}

	private void printStatsInMicro(Method method, long elapsedTime) {
		logger.debug(getPrefixInfo(method).append(getTimeInMicroInfo(elapsedTime / MICRO)));
	}

	private String getTimeInMicroInfo(double elapsedTime) {
		return new StringBuilder(EXECUTED_IND).append(elapsedTime).append(MICRO_DISPLAY).toString();
	}

	private void printStatsInMili(Method method, long elapsedTime) {
		logger.debug(getPrefixInfo(method).append(getTimeInMiliInfo(elapsedTime / MILI)));
	}

	private String getTimeInMiliInfo(double elapsedTime) {
		return new StringBuilder(EXECUTED_IND).append(elapsedTime).append(MILI_DISPLAY).toString();
	}

	private void printStatsInSecond(Method method, long elapsedTime) {
		logger.debug(getPrefixInfo(method).append(getTimeInSecondInfo(elapsedTime / SECOND)));
	}

	private String getTimeInSecondInfo(double elapsedTime) {
		return new StringBuilder(EXECUTED_IND).append(elapsedTime).append(SECOND_DISPLAY).toString();
	}
}