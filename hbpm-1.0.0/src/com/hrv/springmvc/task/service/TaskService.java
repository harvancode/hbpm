package com.hrv.springmvc.task.service;

import java.util.List;

import com.hrv.hbpm.core.BaseTask;
import com.hrv.hbpm.core.CustomTask;
import com.hrv.hbpm.core.EndTask;
import com.hrv.hbpm.core.StartTask;

public interface TaskService {
	public StartTask getStartTask(String serviceId);

	public EndTask getEndTask(String serviceId);

	public List<CustomTask> getAllCustomTaskByServiceId(String serviceId);

	public List<BaseTask> getAllTask();
}