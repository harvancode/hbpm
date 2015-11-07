package com.hrv.core.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextLoader implements ContextLoader {
	private static ApplicationContextLoader context;
	private static String path = "classpath*:applicationContext*.xml";
	private static ApplicationContext applicationContext;

	private ApplicationContextLoader() {
	}

	public static ContextLoader getInstance() {
		if (context == null) {
			context = new ApplicationContextLoader();
		}

		return context;
	}

	public ApplicationContext load() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(path);
		}

		return applicationContext;
	}
}