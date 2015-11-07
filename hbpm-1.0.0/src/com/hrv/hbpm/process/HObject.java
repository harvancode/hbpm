package com.hrv.hbpm.process;

public interface HObject {
	public void init() throws Exception;

	public void setDescription(String description);

	public String getDescription();
}