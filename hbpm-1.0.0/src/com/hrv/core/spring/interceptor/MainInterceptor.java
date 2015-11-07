package com.hrv.core.spring.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Harvan
 * 
 */
public class MainInterceptor implements MethodInterceptor {
	private static final Logger logger = LogManager.getLogger(MainInterceptor.class);

	public Object invoke(MethodInvocation paramMethodInvocation) throws Throwable {
		logger.debug("MainInterceptor invoked.");

		return paramMethodInvocation.proceed();
	}
}