package com.hrv.springmvc.service.viewcontroller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrv.core.mvc.WebController;
import com.hrv.core.spring.interceptor.PerformanceMonitor;
import com.hrv.springmvc.service.model.ServiceVo;

@SuppressWarnings("unchecked")
@Controller
public class ServiceController extends WebController {
	private static final Logger logger = LogManager.getLogger(ServiceController.class);
	private static final String BEAN_ID = "serviceService";
	private static final String SERVICE_LIST = "/service/serviceList";

	@Override
	protected String getBeanId() {
		return BEAN_ID;
	}

	@RequestMapping(value = "/serviceList")
	public String serviceList(ServiceForm serviceForm) {
		try {
			List<ServiceVo> listVo = (List<ServiceVo>) callService(getBeanId(), "getAllService");

			logger.debug(listVo);
		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage(), e);
		}

		return SERVICE_LIST;
	}

	@RequestMapping(value = "/executeService2")
	public String executeService2(ServiceForm serviceForm, final HttpServletRequest request) {
		try {
			final String serviceCode = request.getParameter("serviceCode");

			for (int i = 0; i < 1; i++) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						String result;
						try {
							result = (String) callService(getBeanId(), "executeThreadService", serviceCode);
							// logger.debug(result);
						} catch (Exception e) {
							logger.error("Error on executeService : " + e.getMessage(), e);
						}
					}
				});

				t.start();
			}

			Thread.yield();
		} catch (Exception e) {
			logger.error("Error on executeService : " + e.getMessage(), e);
		}

		return SERVICE_LIST;
	}

	@RequestMapping(value = "/executeService")
	public String executeService(ServiceForm serviceForm, final HttpServletRequest request) {
		try {
			final String serviceCode = request.getParameter("serviceCode");

			try {
				long start, end;

				start = System.currentTimeMillis();

				// for (int i = 0; i < 2000; i++) {
				// String result = (String) callServiceCached(getBeanId(),
				// "executeService", serviceCode);
				// String result = (String) callService(getBeanId(),
				// "executeService", serviceCode);
				String result = (String) callService(getBeanId(), "getExecuteService", serviceCode);
				// String result = (String) callServiceCached(getBeanId(),
				// "getExecuteService", serviceCode);
				// }

				end = System.currentTimeMillis();

				logger.debug("executeService executed in : " + (end - start) + " ms.");
				// logger.debug(result);
			} catch (Exception e) {
				logger.error("Error on executeService : " + e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error("Error on executeService : " + e.getMessage(), e);
		}

		return SERVICE_LIST;
	}

	@RequestMapping(value = "/executeThreadService")
	public String executeThreadService(ServiceForm serviceForm, final HttpServletRequest request) {
		try {
			final String serviceCode = request.getParameter("serviceCode");

			try {
				String result = (String) callService(getBeanId(), "executeThreadService", serviceCode, 1);
			} catch (Exception e) {
				logger.error("Error on executeThreadService : " + e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error("Error on executeThreadService : " + e.getMessage(), e);
		}

		return SERVICE_LIST;
	}

	@RequestMapping(value = "/testService")
	public String testService(ServiceForm serviceForm, final HttpServletRequest request) {
		try {
			long start = System.currentTimeMillis();

			for (int i = 0; i < 200; i++) {
				// callServiceCached(getBeanId(), "testService", "param1",
				// Integer.valueOf(1), "param3", BigDecimal.valueOf(2));
				callService(getBeanId(), "testService", "param1", Integer.valueOf(1), "param3", BigDecimal.valueOf(2));
			}

			logger.debug(PerformanceMonitor.getInstance().getMethodExecutionTimeInfo(start, System.currentTimeMillis(), Thread.currentThread().getStackTrace()));
			// logger.debug(result);
		} catch (Exception e) {
			logger.error(PerformanceMonitor.getInstance().getMethodName(Thread.currentThread().getStackTrace()) + " error : " + e.getMessage(), e);
		}

		return SERVICE_LIST;
	}
}