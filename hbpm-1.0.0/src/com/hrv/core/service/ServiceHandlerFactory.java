package com.hrv.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.hrv.core.hibernate.GenericDao;
import com.hrv.hbpm.exception.HbpmException;

public class ServiceHandlerFactory extends BeanHandlerFactory implements ServiceHandler {
	private static final ServiceHandlerFactory serviceHandlerFactory = new ServiceHandlerFactory();
	private static Map<String, Method> serviceMethodMap = new HashMap<String, Method>();

	private ServiceHandlerFactory() {
	}

	public static ServiceHandler getInstance() {
		return serviceHandlerFactory;
	}

	public Object getService(String serviceName) throws HbpmException, Exception {
		Object service = getBean(serviceName);

		if (service instanceof GenericDao) {
			throw new HbpmException("No service found.");
		}

		return service;
	}

	@Override
	public Object callService(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		Method method = null;

		try {
			Object service = getService(serviceName);

			if (parameters == null) {
				method = service.getClass().getMethod(methodName, new Class[] {});

				return method.invoke(service);
			} else {
				Class<?>[] params = new Class[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					params[i] = parameters[i].getClass();
				}
				method = service.getClass().getMethod(methodName, params);

				return method.invoke(service, parameters);
			}
		} catch (InvocationTargetException ex) {
			Throwable localThrowable = ex.getTargetException();

			if (localThrowable instanceof HbpmException) {
				throw (HbpmException) localThrowable;
			} else if (localThrowable != null) {
				throw (Exception) localThrowable;
			}

			throw ex;
		}
	}

	@SuppressWarnings("rawtypes")
	public Object callServiceCached(String serviceName, String methodName, Object... parameters) throws HbpmException, Exception {
		Method method = null;

		try {
			Object service = getService(serviceName);

			if (parameters == null) {
				if ((method = serviceMethodMap.get(serviceName + methodName)) == null) {
					method = service.getClass().getMethod(methodName, new Class[] {});
					serviceMethodMap.put(serviceName + methodName, method);
				}

				return method.invoke(service);
			} else {
				StringBuffer buff = new StringBuffer();
				Class[] params = new Class[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					params[i] = parameters[i].getClass();
					buff.append(params[i].getName());
				}

				if ((method = serviceMethodMap.get(serviceName + methodName + buff.toString())) == null) {
					method = service.getClass().getMethod(methodName, params);
					serviceMethodMap.put(serviceName + methodName + buff.toString(), method);
				}

				return method.invoke(service, parameters);
			}
		} catch (InvocationTargetException ex) {
			Throwable localThrowable = ex.getTargetException();

			if (localThrowable instanceof HbpmException) {
				throw (HbpmException) localThrowable;
			} else if (localThrowable != null) {
				throw (Exception) localThrowable;
			}

			throw ex;
		}
	}
}