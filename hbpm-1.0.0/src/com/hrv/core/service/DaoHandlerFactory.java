package com.hrv.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.hrv.core.hibernate.DaoLoader;
import com.hrv.core.hibernate.GenericDao;
import com.hrv.hbpm.exception.HbpmException;

public class DaoHandlerFactory extends BeanHandlerFactory implements DaoHandler {
	private static DaoHandlerFactory daoHandlerFactory;

	private DaoHandlerFactory() {
	}

	public static DaoHandler getInstance() {
		if (daoHandlerFactory == null) {
			daoHandlerFactory = new DaoHandlerFactory();
		}

		return daoHandlerFactory;
	}

	public Object getDao(String daoName) throws HbpmException {
		DaoLoader daoLoader = null;
		Object dao = getBean(daoName);

		if (dao == null) {
			daoLoader = (DaoLoader) getBean("daoLoader");
			dao = daoLoader.getDao(daoName);
		}

		if (dao instanceof GenericDao) {
			return dao;
		} else {
			throw new HbpmException("No dao found.");
		}
	}

	@SuppressWarnings("rawtypes")
	public Object callDao(String daoName, String methodName, Object... parameters) throws HbpmException, Exception {
		try {
			Method method = null;
			Object dao = null;

			dao = getDao(daoName);

			if (parameters == null) {
				method = dao.getClass().getMethod(methodName, new Class[] {});
				return method.invoke(dao);
			} else {
				Class[] params = new Class[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					params[i] = parameters[i].getClass();
				}
				method = dao.getClass().getMethod(methodName, params);
				return method.invoke(dao, parameters);
			}
		} catch (InvocationTargetException e) {
			Throwable localThrowable = e.getTargetException();

			if (localThrowable instanceof HbpmException) {
				throw (HbpmException) localThrowable;
			} else if (localThrowable != null) {
				throw (Exception) localThrowable;
			}

			throw e;
		}
	}
}