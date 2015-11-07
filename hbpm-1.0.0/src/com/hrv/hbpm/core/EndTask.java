package com.hrv.hbpm.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.hbpm.process.HFinalTask;

public class EndTask extends BaseTask implements HFinalTask {
	private static Logger logger = LogManager.getLogger(EndTask.class);
	private boolean init = false;

	@Override
	public void validateComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
//		logger.debug("Execute EndTask : ".concat(getDescription()).concat("."));
	}

	@Override
	public void init() throws Exception {
		if (!init) {
			init = true;
			logger.debug("Initialization EndTask : " + getDescription());
		}
	}

}