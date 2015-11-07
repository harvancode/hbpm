package com.hrv.hbpm.exception;

public class HbpmException extends Exception {
	public HbpmException() {
	}

	public HbpmException(String message, Throwable cause) {
		super(message, cause);
	}

	public HbpmException(Throwable cause) {
		super(cause);
	}

	public HbpmException(String message) {
		super(message);
	}
}