package com.hrv.hbpm.core;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HSequenceFlow;
import com.hrv.hbpm.process.HTransitionTask;

public abstract class TransitionTask extends BaseTask implements HTransitionTask {
	private static Logger logger = LogManager.getLogger(TransitionTask.class);
	private List<SequenceFlow> sequenceFlowList;

	@Override
	public void setSequenceFlow(List<SequenceFlow> secuenceFlowList) {
		this.sequenceFlowList = secuenceFlowList;
	}

	@Override
	public List<SequenceFlow> getSequenceFlows() {
		return sequenceFlowList;
	}

	protected HSequenceFlow getValidatedSequenceFlow() throws HbpmException {
		HSequenceFlow selectedFlow = null;
		if (getSequenceFlows() == null) {
			throw new HbpmException("No SequenceFlow found on ".concat(getDescription()).concat("."));
		}

		for (HSequenceFlow flow : getSequenceFlows()) {
			if (flow.validate()) {
				selectedFlow = flow;
				break;
			}
		}

		return selectedFlow;
	}

	protected void initSequenceFlow() {
		List<SequenceFlow> sequenceFlows = getSequenceFlows();

		for (SequenceFlow sequenceFlow : sequenceFlows) {
			try {
				sequenceFlow.init();
			} catch (Exception e) {
				logger.error("Error initialization : " + e.getMessage(), e);
			}
		}
	}
}
