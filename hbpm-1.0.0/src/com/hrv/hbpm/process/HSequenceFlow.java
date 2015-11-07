package com.hrv.hbpm.process;

public interface HSequenceFlow extends HObject, HValidator, HScript {
	public void setSequenceNumber(int sequenceNumber);

	public int getSequenceNumber();

	public void setTask(HTask task);

	public void setParentTask(HTask task);

	public HTask getTask();
}