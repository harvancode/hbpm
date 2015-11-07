/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.mvc;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.core.service.ServiceHandlerFactory;
import com.hrv.hbpm.exception.HbpmException;

/**
 * 
 * @author harvan
 */
public abstract class WebController {
	private static final Logger logger = LogManager.getLogger(WebController.class);

	public WebController() {
	}

	public Object callService(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		return ServiceHandlerFactory.getInstance().callService(serviceName, methodName, parameters);
	}

	public Object callServiceCached(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		return ServiceHandlerFactory.getInstance().callServiceCached(serviceName, methodName, parameters);
	}

	protected abstract String getBeanId();

	@SuppressWarnings("rawtypes")
	protected Map getDescribedObject(Object object) {
		Map map = null;

		try {
			map = BeanUtils.describe(object);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return map;
	}
}