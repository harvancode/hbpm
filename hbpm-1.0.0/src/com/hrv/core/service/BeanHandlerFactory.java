package com.hrv.core.service;

import org.springframework.context.ApplicationContext;

import com.hrv.core.spring.context.ApplicationContextLoader;

public class BeanHandlerFactory {
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	static {
		applicationContext = ApplicationContextLoader.getInstance().load();
	}
}