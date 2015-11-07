package com.hrv.core.service;

import com.hrv.hbpm.exception.HbpmException;

public class DefaultService implements ServiceHandler, DaoHandler {

	@Override
	public Object getService(String serviceName) throws HbpmException, Exception {
		return ServiceHandlerFactory.getInstance().getService(serviceName);
	}

	public Object callService(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		return ServiceHandlerFactory.getInstance().callService(serviceName, methodName, parameters);
	}

	@Override
	public Object callServiceCached(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		return ServiceHandlerFactory.getInstance().callServiceCached(serviceName, methodName, parameters);
	}

	@Override
	public Object getDao(String daoName) throws HbpmException {
		return DaoHandlerFactory.getInstance().getDao(daoName);
	}

	@Override
	public Object callDao(String daoName, String methodName, Object... parameters) throws HbpmException, Exception {
		return DaoHandlerFactory.getInstance().callDao(daoName, methodName, parameters);
	}
}