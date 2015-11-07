package com.hrv.hbpm.core;

import com.hrv.hbpm.process.HObject;

public abstract class BaseObject implements HObject {
	private String description;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}