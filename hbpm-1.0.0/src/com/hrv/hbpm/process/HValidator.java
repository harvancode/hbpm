package com.hrv.hbpm.process;

import com.hrv.hbpm.exception.HbpmException;

public interface HValidator extends HEvent {
	public boolean validate() throws HbpmException;

	public void validateComponent() throws HbpmException;
}