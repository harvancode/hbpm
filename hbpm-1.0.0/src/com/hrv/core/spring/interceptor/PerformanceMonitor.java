package com.hrv.core.spring.interceptor;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.core.utils.BeanPropertyUtils;

/**
 * <pre>
 * Programmatic & Proxy based - Performance Monitoring.
 * </pre>
 * 
 * @author Harvan Irsyadi
 * @version 1.1, 11/18/15
 * 
 */
public class PerformanceMonitor implements MethodInterceptor {
	private static PerformanceMonitor instance;
	private static final double MILIS = 1000 * 1000;
	private static final String MILIS_UNIT = " ms.";
	private static final String INFO_EXECUTED_IN = " executed in : ";
	private Logger logger;
	private Set<String> methodList = new HashSet<String>();
	private MethodInterceptor mainInterceptor;

	private PerformanceMonitor() {
	}

	public static final PerformanceMonitor getInstance() {
		if (instance == null) {
			instance = new PerformanceMonitor();
		}

		return instance;
	}

	private String getMethodName(StackTraceElement e[]) {
		return BeanPropertyUtils.getMethodName(e);
	}

	public String getMethodExecutionTimeInfo(long start, long end, StackTraceElement e[]) {
		return new StringBuffer(getMethodName(e)).append(INFO_EXECUTED_IN).append((end - start)).append(MILIS_UNIT).toString();
	}

	private String getMethodExecutionTimeInfo(long start, long end, Method method) {
		return new StringBuffer(method.getName()).append(INFO_EXECUTED_IN).append((end - start) / MILIS).append(MILIS_UNIT).toString();
	}

	public void setMainInterceptor(MethodInterceptor mainInterceptor) {
		this.mainInterceptor = mainInterceptor;
	}

	public void setMethodList(Set<String> methodList) {
		this.methodList = methodList;
	}

	public Object invoke(MethodInvocation paramMethodInvocation) throws Throwable {
		Method method = paramMethodInvocation.getMethod();
		boolean containsMethod = methodList.contains(method.getName());
		long start = 0;

		if (containsMethod) {
			start = System.nanoTime();
		}

		try {
			if (mainInterceptor != null) {
				return mainInterceptor.invoke(paramMethodInvocation);
			} else {
				return paramMethodInvocation.proceed();
			}
		} finally {
			if (containsMethod) {
				if (logger == null) {
					logger = LogManager.getLogger(method.getDeclaringClass().getName());
				}

				logger.debug(getMethodExecutionTimeInfo(start, System.nanoTime(), method));
			}
		}
	}
}