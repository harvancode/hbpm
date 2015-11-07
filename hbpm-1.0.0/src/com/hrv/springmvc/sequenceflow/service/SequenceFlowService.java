package com.hrv.springmvc.sequenceflow.service;

import java.util.List;

import com.hrv.hbpm.core.SequenceFlow;

public interface SequenceFlowService {
	public List<SequenceFlow> getSequenceFlowByParentTaskId(String taskId);
}