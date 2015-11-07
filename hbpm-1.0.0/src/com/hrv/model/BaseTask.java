package com.hrv.model;

import java.sql.Clob;

import com.hrv.hbpm.core.BaseObject;
import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HTask;

public class BaseTask extends BaseObject implements HTask {
	private String id;
	private Clob onEnterScript;
	private Clob onLeaveScript;
	private Clob script;
	private Service service;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Clob getOnEnterScript() {
		return onEnterScript;
	}

	public void setOnEnterScript(Clob onEnterScript) {
		this.onEnterScript = onEnterScript;
	}

	public Clob getOnLeaveScript() {
		return onLeaveScript;
	}

	public void setOnLeaveScript(Clob onLeaveScript) {
		this.onLeaveScript = onLeaveScript;
	}

	public Clob getScript() {
		return script;
	}

	public void setScript(Clob script) {
		this.script = script;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws HbpmException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() throws HbpmException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validateComponent() throws HbpmException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub

	}
}