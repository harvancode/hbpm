package com.hrv.springmvc.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hrv.core.service.DefaultService;
import com.hrv.hbpm.core.CustomTask;
import com.hrv.hbpm.core.EndTask;
import com.hrv.hbpm.core.SequenceFlow;
import com.hrv.hbpm.core.Service;
import com.hrv.hbpm.core.StartTask;
import com.hrv.hbpm.core.TransitionTask;
import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HCustomTask;
import com.hrv.hbpm.process.HFinalTask;
import com.hrv.hbpm.process.HTask;
import com.hrv.springmvc.sequenceflow.service.SequenceFlowService;
import com.hrv.springmvc.service.model.ServiceVo;
import com.hrv.springmvc.service.service.ServiceDao;
import com.hrv.springmvc.service.service.ServiceService;
import com.hrv.springmvc.task.service.TaskService;

public class ServiceServiceImpl extends DefaultService implements ServiceService {
	private static final Logger logger = LogManager.getLogger(ServiceServiceImpl.class);
	private static Map<String, Service> serviceMap;
	private ServiceDao serviceDao;
	private TaskService taskService;
	private SequenceFlowService sequenceFlowService;
	private ThreadPoolTaskExecutor taskExecutor;

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setSequenceFlowService(SequenceFlowService sequenceFlowService) {
		this.sequenceFlowService = sequenceFlowService;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	@Override
	public List<ServiceVo> getAllService() {
		List<Service> list = serviceDao.getAll();
		List<ServiceVo> listVo = new ArrayList<ServiceVo>();

		ServiceVo newVo;
		for (Service service : list) {
			if (!serviceMap.containsKey(service.getCode())) {
				newVo = new ServiceVo();

				newVo.setId(service.getId());
				newVo.setDescription(service.getDescription());
				newVo.setCode(service.getCode());

				listVo.add(newVo);

				try {
					prepareStartTask(service);
					prepareCustomTask(service);
					prepareEndTask(service);

					service.init();
					serviceMap.put(service.getCode(), service);

					logger.debug("Service : \"" + service.getCode() + "\" has been loaded.");
				} catch (HbpmException e) {
					System.err.println("HbpmException getAllService : " + e);
					e.printStackTrace();
				} catch (Exception e) {
					System.err.println(" getAllService : " + e);
					e.printStackTrace();
				}
			}
		}

		return listVo;
	}

	private void prepareStartTask(Service service) throws HbpmException {
		TransitionTask start = taskService.getStartTask(service.getId());

		service.addStartTask(start);

		List<SequenceFlow> sequenceFlowListStart = sequenceFlowService.getSequenceFlowByParentTaskId(start.getId());

		start.setSequenceFlow(sequenceFlowListStart);

		for (SequenceFlow seqFlow : sequenceFlowListStart) {
			service.addObject(SequenceFlow.class.getName().concat(seqFlow.getId()), seqFlow);
		}
	}

	private void prepareCustomTask(Service service) throws HbpmException {
		List<CustomTask> allCustomTask = taskService.getAllCustomTaskByServiceId(service.getId());

		for (CustomTask task : allCustomTask) {
			service.addObject(task.getClass().getName().concat(task.getId()), task);

			List<SequenceFlow> sequenceFlowListCustom = sequenceFlowService.getSequenceFlowByParentTaskId(task.getId());
			task.setSequenceFlow(sequenceFlowListCustom);

			for (SequenceFlow seqFlow : sequenceFlowListCustom) {
				service.addObject(SequenceFlow.class.getName().concat(seqFlow.getId()), seqFlow);
			}
		}
	}

	private void prepareEndTask(Service service) throws HbpmException {
		EndTask finalTask = taskService.getEndTask(service.getId());
		service.addObject(EndTask.class.getName().concat(finalTask.getId()), finalTask);
	}

	public static final int NANO = 1;
	public static final int MICRO = 1000;
	public static final int MILI = 1000000;
	public static final int SECOND = 1000000000;
	public static final String NANO_DISPLAY = "ns";
	public static final String MICRO_DISPLAY = "µs";
	public static final String MILI_DISPLAY = "ms";
	public static final String SECOND_DISPLAY = "s";

	public String executeService(String serviceCode) throws HbpmException, Exception {
		Service service;

		long start, end;

		start = System.nanoTime();
		if ((service = serviceMap.get(serviceCode)) != null) {
			service = (Service) service.clone();

			service.execute();
		} else {
			throw new HbpmException("Service not found");
		}

		for (int i = 0; i < 10000; i++) {
			String s = "lsafdjlasjdflasdjfljsadflasldfjalsdjflasjdf";
			s = s + i;
			s = s + i;
			s = s + i;
		}

		end = System.nanoTime();

		return new StringBuffer("Service : (").append(service.hashCode()).append(")").append(serviceCode).append(" executed in : ").append(Double.valueOf((end - start)) / MICRO)
				.append(" ").append(MICRO_DISPLAY).append("").toString();
	}

	public String executeThreadService(String serviceCode, Integer size) throws HbpmException, Exception {
		StringBuffer buff = new StringBuffer();
		ServiceExecutor[] se = new ServiceExecutor[size];
		String[] resultMessage = new String[size];
		ServiceService serviceServiceByProxy = (ServiceService) getService("serviceService");
		ServiceService serviceServiceNoProxy = this;

		for (int i = 0; i < se.length; i++) {
			se[i] = (ServiceExecutor) getService("serviceExecutor");
			se[i].setServiceCode(serviceCode);
			se[i].setServiceService(serviceServiceNoProxy);
			// se[i].setServiceService(serviceServiceByProxy);
		}

		for (int i = 0; i < se.length; i++) {
			taskExecutor.execute(se[i]);
		}

		int count = -1;

		for (;;) {
			count = taskExecutor.getActiveCount();

			if (count == 0) {
				break;
			}
		}

		buff.append("\n");

		boolean allMessageCatched = false;

		for (; !allMessageCatched;) {
			int currentEmptyCount = 0;

			for (int i = 0; i < resultMessage.length; i++) {
				if (resultMessage[i] == null) {
					resultMessage[i] = se[i].getResultMessage();

					if (resultMessage[i] == null) {
						currentEmptyCount++;
					}
				}
			}

			if (currentEmptyCount == 0) {
				allMessageCatched = true;
			}
		}

		for (int i = 0; i < resultMessage.length; i++) {
			buff.append(i).append(" : ").append(resultMessage[i]).append("\n");
		}

		return buff.toString();
	}

	public void test() {
		try {
			Service service = new Service();
			// start - start
			StartTask start = new StartTask();
			start.setDescription("Start");

			service.addStartTask(start);

			// start sequence - start
			SequenceFlow seqStart1 = new SequenceFlow();
			service.addObject("seqStart1", seqStart1);

			seqStart1.setDescription("sequence start 1");
			seqStart1.setSequenceNumber(0);
			seqStart1.setScript(Hibernate.createClob("return true;"));

			HCustomTask cust1 = new CustomTask();
			service.addObject("cust1", cust1);

			StringBuilder sb = new StringBuilder();

			sb.append("import java.lang.String;\n");
			sb.append("String str1 = \"Test String\";\n");
			sb.append("System.out.println(str1);");
			cust1.setDescription("Task Custom 1");
			cust1.setScript(Hibernate.createClob(sb.toString()));

			seqStart1.setTask((HTask) cust1);
			seqStart1.setParentTask(start);

			List<SequenceFlow> seqListStart = new ArrayList<SequenceFlow>();
			seqListStart.add(seqStart1);
			// start sequence - end

			start.setSequenceFlow(seqListStart);
			// start - end

			// custom - start
			// custom sequence - start
			List<SequenceFlow> seqListCustom = new ArrayList<SequenceFlow>();

			SequenceFlow seqCustom1 = new SequenceFlow();
			service.addObject("seqCustom1", seqCustom1);

			seqCustom1.setDescription("sequence custom 1");
			seqCustom1.setSequenceNumber(0);
			seqCustom1.setScript(Hibernate.createClob("return false;"));
			seqListCustom.add(seqCustom1);

			SequenceFlow seqCustom2 = new SequenceFlow();
			service.addObject("seqCustom2", seqCustom2);

			seqCustom2.setDescription("sequence custom 2");
			seqCustom2.setSequenceNumber(0);
			seqCustom2.setScript(Hibernate.createClob("return true;"));
			seqListCustom.add(seqCustom2);

			HFinalTask finalTask = new EndTask();
			service.addObject("finalTask", finalTask);

			finalTask.setDescription("Final Task");
			seqCustom2.setTask((HTask) finalTask);

			cust1.setSequenceFlow(seqListCustom);

			service.init();
			service.execute();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	static {
		serviceMap = new Hashtable<String, Service>();
	}

	public String getExecuteService(String serviceCode) throws HbpmException, Exception {
		return executeService(serviceCode);
	}

	@Override
	public void testService(String param1, Integer param2, String param3, BigDecimal param4) throws HbpmException {
		logger.debug(param1 + ", " + param2 + ", " + param3 + ", " + param4);
	}
}