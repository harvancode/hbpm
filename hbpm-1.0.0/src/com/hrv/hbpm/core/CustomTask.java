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
import com.hrv.hbpm.process.HCustomTask;
import com.hrv.hbpm.process.HSequenceFlow;

public class CustomTask extends TransitionTask implements HCustomTask {
	private static final Logger logger = LogManager.getLogger(CustomTask.class);
	private Clob script;
	private HCustomHandler customHandler;

	@Override
	public void setScript(Clob script) {
		this.script = script;
	}

	@Override
	public Clob getScript() {
		return script;
	}

	@Override
	public void validateComponent() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void init() throws Exception {
		if (customHandler == null) {
			logger.debug("Initialization CustomTask : " + getDescription());

			try {
				customHandler = (HCustomHandler) ScriptEvaluator.createFastScriptEvaluator(new Scanner(null, new StringReader(ClobUtils.convertClobToString(getScript()))),
						HCustomHandler.class, new String[] { "param1" }, super.getClass().getClassLoader());
			} catch (CompileException e) {
				throw new HbpmException(e.getMessage());
			} catch (IOException e) {
				throw new HbpmException(e.getMessage());
			} catch (Exception e) {
				throw new HbpmException(e.getMessage());
			}

			initSequenceFlow();
		}
	}

	@Override
	public void execute() throws HbpmException {
		if (customHandler != null) {
			customHandler.execute(null);
		}

		HSequenceFlow sequenceFlow = getValidatedSequenceFlow();
		if (sequenceFlow == null) {
			throw new HbpmException("No SequenceFlow validated in : ".concat(getDescription()).concat("."));
		}
		sequenceFlow.getTask().execute();
	}

	public abstract static interface HCustomHandler {
		public void execute(String param1) throws HbpmException;
	}
}