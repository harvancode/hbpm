package com.hrv.springmvc.service.service;

import java.util.List;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.springmvc.service.model.ServiceVo;

public interface ServiceService {
	public List<ServiceVo> getAllService();

	public String executeService(String serviceCode) throws HbpmException, Exception;

	public String executeThreadService(String serviceCode, Integer size) throws HbpmException, Exception;

	public void testService() throws HbpmException;
}