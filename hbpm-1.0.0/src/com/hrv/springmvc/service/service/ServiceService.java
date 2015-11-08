package com.hrv.springmvc.service.service;

import java.math.BigDecimal;
import java.util.List;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.springmvc.service.model.ServiceVo;

public interface ServiceService {
	public List<ServiceVo> getAllService();

	public String executeService(String serviceCode) throws HbpmException, Exception;

	public String executeThreadService(String serviceCode, Integer size) throws HbpmException, Exception;

	public void testService(String param1, Integer param2, String param3, BigDecimal param4) throws HbpmException;
}