package com.hrv.springmvc.service.model;

import java.io.Serializable;

public class ServiceVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2365435253344374129L;
	private String id;
	private String code;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}