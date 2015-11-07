package com.hrv.hbpm.core;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Clob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.ScriptEvaluator;

import com.hrv.core.utils.ClobUtils;
import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HSequenceFlow;
import com.hrv.hbpm.process.HTask;

public class SequenceFlow extends BaseObject implements HSequenceFlow {
	private static Logger logger = LogManager.getLogger(SequenceFlow.class);
	private String id;
	private int version;
	private Clob onEnterScript;
	private Clob onLeaveScript;
	private BaseTask task;
	private BaseTask parentTask;
	private Clob script;
	private HSequenceFlowHandler sequenceFlowHandler;
	private int sequenceNumber;

	@Override
	public void validateComponent() {
		// TODO Auto-generated method stub

	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public void setScript(Clob script) {
		this.script = script;

	}

	@Override
	public Clob getScript() {
		return script;
	}

	@Override
	public void setSequenceNumber(int sequence) {
		this.sequenceNumber = sequence;

	}

	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HSequenceFlowHandler getSequenceFlowHandler() {
		return sequenceFlowHandler;
	}

	public void setSequenceFlowHandler(HSequenceFlowHandler sequenceFlowHandler) {
		this.sequenceFlowHandler = sequenceFlowHandler;
	}

	public Clob getOnEnterScript() {
		return onEnterScript;
	}

	public Clob getOnLeaveScript() {
		return onLeaveScript;
	}

	public void setParentTask(BaseTask parentTask) {
		this.parentTask = parentTask;
	}

	@Override
	public void setTask(HTask task) {
		this.task = (BaseTask) task;
	}

	@Override
	public HTask getTask() {
		return task;
	}

	public HTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(HTask parentTask) {
		this.parentTask = (BaseTask) parentTask;
	}

	public void setTask(BaseTask task) {
		this.task = task;
	}

	@Override
	public void setOnEnterScript(Clob onEnterScript) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOnLeaveScript(Clob onLeaveScript) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void init() throws Exception {
		if (sequenceFlowHandler == null) {
			logger.debug("Initialization SequenceFlow : " + getDescription());
			String script = ClobUtils.convertClobToString(getScript());

			if (script != null) {
				try {
					sequenceFlowHandler = (HSequenceFlowHandler) ScriptEvaluator.createFastScriptEvaluator(new Scanner(null, new StringReader(script)), HSequenceFlowHandler.class,
							new String[] { "param1" }, super.getClass().getClassLoader());
				} catch (CompileException e) {
					throw new HbpmException(e.getMessage());
				} catch (IOException e) {
					throw new HbpmException(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					throw new HbpmException(e.getMessage());
				}
			}

			getTask().init();
		}
	}

	@Override
	public boolean validate() throws HbpmException {
		// logger.debug("Execute validate SequenceFlow : ".concat(getDescription()).concat("."));

		if (sequenceFlowHandler == null) {
			return true;
		} else {
			try {
				return sequenceFlowHandler.validate("param1");
			} catch (Exception e) {
				throw new HbpmException(e);
			}
		}
	}

	public abstract static interface HSequenceFlowHandler {
		public boolean validate(String param1) throws Exception;
	}

}
