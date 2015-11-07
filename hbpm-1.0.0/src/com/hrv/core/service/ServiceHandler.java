package com.hrv.core.service;

import com.hrv.hbpm.exception.HbpmException;

public interface ServiceHandler {

	public Object getService(String serviceName) throws HbpmException, Exception;

	public Object callService(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception;

	public Object callServiceCached(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception;
}