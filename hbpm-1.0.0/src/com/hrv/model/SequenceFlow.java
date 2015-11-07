package com.hrv.model;

public class SequenceFlow {
	private String id;
	private String onEnterScript;
	private String onLeaveScript;
	private String script;
	private Integer sequenceNumber;
	private BaseTask task;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnEnterScript() {
		return onEnterScript;
	}

	public void setOnEnterScript(String onEnterScript) {
		this.onEnterScript = onEnterScript;
	}

	public String getOnLeaveScript() {
		return onLeaveScript;
	}

	public void setOnLeaveScript(String onLeaveScript) {
		this.onLeaveScript = onLeaveScript;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public BaseTask getTask() {
		return task;
	}

	public void setTask(BaseTask task) {
		this.task = task;
	}
}