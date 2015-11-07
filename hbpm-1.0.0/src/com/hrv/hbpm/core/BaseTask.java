package com.hrv.hbpm.core;

import java.sql.Clob;

import com.hrv.hbpm.process.HTask;

public abstract class BaseTask extends BaseObject implements HTask {
	private String id;
	private int version;
	private Clob onEnterScript;
	private Clob onLeaveScript;
	private Service service;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
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