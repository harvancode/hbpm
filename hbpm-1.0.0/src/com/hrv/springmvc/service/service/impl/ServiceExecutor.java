package com.hrv.springmvc.service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.springmvc.service.service.ServiceService;

public class ServiceExecutor implements Runnable {
	private static final Logger logger = LogManager.getLogger(ServiceExecutor.class);
	private String serviceCode;
	private ServiceService serviceService;
	private String resultMessage;

	public ServiceExecutor() {
	}

	public ServiceExecutor(String serviceCode, ServiceService serviceService) {
		this.serviceCode = serviceCode;
		this.serviceService = serviceService;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	@Override
	public void run() {
		try {
			resultMessage = serviceService.executeService(serviceCode);
		} catch (HbpmException e) {
			logger.error("HbpmException : " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage(), e);
		}
	}
}
