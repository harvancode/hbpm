package com.hrv.hbpm.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HSequenceFlow;

public class StartTask extends TransitionTask {
	private static Logger logger = LogManager.getLogger(StartTask.class);

	@Override
	public void validateComponent() throws HbpmException {
		validateSequence();
	}

	private void validateSequence() throws HbpmException {
		if (getSequenceFlows() == null) {
			throw new HbpmException("No sequence found");
		}
	}

	@Override
	public void execute() throws HbpmException {
		HSequenceFlow sequenceFlow = getValidatedSequenceFlow();

		if (sequenceFlow == null) {
			throw new HbpmException("No SequenceFlow validated in : ".concat(getDescription()).concat("."));
		}

		// logger.debug("Execute " + sequenceFlow.getTask());

		sequenceFlow.getTask().execute();
	}

	@Override
	public void init() throws Exception {
		logger.debug("Initialization StartTask :" + getDescription());
		initSequenceFlow();
	}
}