package com.hrv.springmvc.task.service;

import java.util.List;

import com.hrv.core.service.Dao;
import com.hrv.hbpm.core.BaseTask;
import com.hrv.hbpm.core.CustomTask;

public interface TaskDao extends Dao<BaseTask> {
	public BaseTask getTaskByServiceId(Class<?> clazz, String serviceId);

	public List<CustomTask> getAllCustomTaskByServiceId(String serviceId);
}