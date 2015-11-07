package com.hrv.springmvc.sequenceflow.service.impl;

import java.util.List;

import com.hrv.core.service.DefaultService;
import com.hrv.hbpm.core.SequenceFlow;
import com.hrv.springmvc.sequenceflow.service.SequenceFlowDao;
import com.hrv.springmvc.sequenceflow.service.SequenceFlowService;

public class SequenceFlowServiceImpl extends DefaultService implements SequenceFlowService {
	private SequenceFlowDao sequenceFlowDao;

	public void setSequenceFlowDao(SequenceFlowDao sequenceFlowDao) {
		this.sequenceFlowDao = sequenceFlowDao;
	}

	@Override
	public List<SequenceFlow> getSequenceFlowByParentTaskId(String taskId) {
		return sequenceFlowDao.getSequenceFlowByParentTaskId(taskId);
	}
}