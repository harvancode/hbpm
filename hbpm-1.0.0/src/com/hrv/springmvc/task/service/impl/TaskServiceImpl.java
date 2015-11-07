package com.hrv.springmvc.task.service.impl;

import java.util.List;

import com.hrv.core.service.DefaultService;
import com.hrv.hbpm.core.BaseTask;
import com.hrv.hbpm.core.CustomTask;
import com.hrv.hbpm.core.EndTask;
import com.hrv.hbpm.core.StartTask;
import com.hrv.springmvc.task.model.BaseTaskVo;
import com.hrv.springmvc.task.service.TaskDao;
import com.hrv.springmvc.task.service.TaskService;

public class TaskServiceImpl extends DefaultService implements TaskService {
	private TaskDao taskDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public StartTask getStartTask(String serviceId) {
		return (StartTask) taskDao.getTaskByServiceId(StartTask.class, serviceId);
	}

	public EndTask getEndTask(String serviceId) {
		return (EndTask) taskDao.getTaskByServiceId(EndTask.class, serviceId);
	}

	public List<CustomTask> getAllCustomTaskByServiceId(String serviceId) {
		return taskDao.getAllCustomTaskByServiceId(serviceId);
	}

	public List<BaseTask> getAllTask() {
		return taskDao.getAll();
	}

	private void copyProperty(StartTask source, BaseTaskVo dest) {
		dest.setId(source.getId());
		dest.setDescription(source.getDescription());
		// dest.setOnEnterScript(source.getOnEnterScript());
		// dest.setOnLeaveScript(source.getOnLeaveScript());
		dest.setServiceId(source.getService().getId());
		dest.setServiceCode(source.getService().getCode());
		dest.setServiceDescription(source.getService().getDescription());
	}
}