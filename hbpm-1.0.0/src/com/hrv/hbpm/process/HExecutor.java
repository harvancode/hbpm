package com.hrv.hbpm.process;

import com.hrv.hbpm.exception.HbpmException;

public interface HExecutor extends HValidator {
	public void execute() throws HbpmException;
}