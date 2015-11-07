package com.hrv.springmvc.task.model;

import java.io.Serializable;

public class BaseTaskVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -651403443900707941L;
	private String id;
	private String description;
	private String onEnterScript;
	private String onLeaveScript;
	private String serviceId;
	private String serviceCode;
	private String serviceDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
}