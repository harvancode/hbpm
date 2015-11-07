package com.hrv.springmvc.sequenceflow.service;

import java.util.List;

import com.hrv.core.service.Dao;
import com.hrv.hbpm.core.SequenceFlow;

public interface SequenceFlowDao extends Dao<SequenceFlow> {
	public List<SequenceFlow> getSequenceFlowByParentTaskId(String parentTaskId);
}