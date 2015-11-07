package com.hrv.springmvc.task.service.impl;

import java.util.List;

import com.hrv.core.hibernate.GenericDao;
import com.hrv.hbpm.core.BaseTask;
import com.hrv.hbpm.core.CustomTask;
import com.hrv.springmvc.task.service.TaskDao;

public class TaskDaoImpl extends GenericDao<BaseTask> implements TaskDao {

	@Override
	public BaseTask getTaskByServiceId(Class<?> clazz, String serviceId) {
		return (BaseTask) getSession().createQuery(new StringBuffer("FROM ").append(clazz.getName()).append(" where service.id =:serviceId").toString())
				.setString("serviceId", serviceId).uniqueResult();
	}

	@Override
	public List<CustomTask> getAllCustomTaskByServiceId(String serviceId) {
		return getSession().createQuery(new StringBuffer("FROM ").append(CustomTask.class.getName()).append(" WHERE service.id =:serviceId").toString())
				.setString("serviceId", serviceId).list();
	}
}