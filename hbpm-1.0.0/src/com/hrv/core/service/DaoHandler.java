package com.hrv.core.service;

import com.hrv.hbpm.exception.HbpmException;

public interface DaoHandler {
	public Object getDao(String daoName) throws HbpmException;

	public Object callDao(String daoName, String methodName, Object... parameters) throws HbpmException, Exception;
}