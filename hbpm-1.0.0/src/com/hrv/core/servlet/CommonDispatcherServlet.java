package com.hrv.core.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;

import com.hrv.core.service.ServiceHandlerFactory;

public class CommonDispatcherServlet extends DispatcherServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3160812489872961942L;
	private static final Logger logger = LogManager.getLogger(CommonDispatcherServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		logger.debug("Initializing service handler started...");
		ServiceHandlerFactory.getInstance();
		logger.debug("Initializing service handler finished...");
	}
}
