package com.hrv.hbpm.process;

import java.util.List;

import com.hrv.hbpm.core.SequenceFlow;

public interface HTransitionTask extends HTask {
	public void setSequenceFlow(List<SequenceFlow> secuenceFlowList);

	public List<SequenceFlow> getSequenceFlows();
}