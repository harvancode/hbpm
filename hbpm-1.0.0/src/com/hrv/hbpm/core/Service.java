package com.hrv.hbpm.core;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HObject;
import com.hrv.hbpm.process.HService;
import com.hrv.hbpm.process.HTransitionTask;

public class Service implements HService, Cloneable {
	private static Logger logger = LogManager.getLogger(Service.class);
	private String id;
	private int version;
	private String code;
	private String description;
	private Timestamp effectiveDate;
	private int serviceVersion;
	private HTransitionTask start;
	private Map<String, HObject> objectMaps = new HashMap<String, HObject>();

	public Service() {
	}

	public void addStartTask(HTransitionTask start) {
		this.start = start;
		objectMaps.put("start", start);
	}

	public HTransitionTask getStartTask() {
		return start;
	}

	public void addObject(String id, HObject object) throws HbpmException {
		if (objectMaps.get(id) != null) {
			throw new HbpmException("Object with id \"".concat(id).concat("\"").concat(" exist."));
		}
		objectMaps.put(id, object);
	}

	public void removeObject(String id) throws HbpmException {
		if (objectMaps.get(id) == null) {
			throw new HbpmException("Object with id \"".concat(id).concat("\"").concat(" does not exist."));
		}
		objectMaps.remove(id);
	}

	public void execute() {
		try {
			start.execute();
		} catch (HbpmException e) {
			logger.error("Error on execute : " + e.getMessage(), e);
		}
	}

	public void init() throws Exception {
		getStartTask().init();
	}

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

	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public int getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(int serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}