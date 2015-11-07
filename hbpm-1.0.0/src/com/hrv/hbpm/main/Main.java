package com.hrv.hbpm.main;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.janino.SimpleCompiler;
import org.hibernate.Hibernate;

import com.hrv.component.utils.sourcecode.ClassReturnType;
import com.hrv.component.utils.sourcecode.Modifier;
import com.hrv.component.utils.sourcecode.VoidReturnType;
import com.hrv.component.utils.sourcecode.builder.ClassBuilder;
import com.hrv.component.utils.sourcecode.builder.CommonMethodBuilder;
import com.hrv.component.utils.sourcecode.wrapper.ClassWrapper;
import com.hrv.component.utils.sourcecode.wrapper.CommonMethodWrapper;
import com.hrv.hbpm.core.CustomTask;
import com.hrv.hbpm.core.EndTask;
import com.hrv.hbpm.core.SequenceFlow;
import com.hrv.hbpm.core.Service;
import com.hrv.hbpm.core.StartTask;
import com.hrv.hbpm.exception.HbpmException;
import com.hrv.hbpm.process.HCustomTask;
import com.hrv.hbpm.process.HFinalTask;
import com.hrv.hbpm.process.HSequenceFlow;
import com.hrv.hbpm.process.HTask;

public class Main {
	private static final int MILISECONDS = 1000;

	public Main() {

	}

	public Service createService1() throws HbpmException {
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
		seqStart1.setScript(Hibernate.createClob(getSequenceFlowStart().toString()));

		HCustomTask cust1 = new CustomTask();
		service.addObject("cust1", cust1);

		cust1.setDescription("Task Custom 1");
		cust1.setScript(Hibernate.createClob(getCustomScript1().toString()));

		seqStart1.setTask((HTask) cust1);

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
		seqCustom1.setScript(Hibernate.createClob(getSequenceFlowCustomScript1().toString()));
		seqListCustom.add(seqCustom1);

		SequenceFlow seqCustom2 = new SequenceFlow();
		service.addObject("seqCustom2", seqCustom2);

		seqCustom2.setDescription("sequence custom 2");
		seqCustom2.setSequenceNumber(0);
		seqCustom2.setScript(Hibernate.createClob(getSequenceFlowCustomScript2().toString()));
		seqListCustom.add(seqCustom2);

		HFinalTask finalTask = new EndTask();
		service.addObject("finalTask", finalTask);

		finalTask.setDescription("Final Task");
		seqCustom2.setTask((HTask) finalTask);

		cust1.setSequenceFlow(seqListCustom);
		// custom sequence - end
		// custom - end
		return service;
	}

	public Service createService2() throws HbpmException {
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
		seqStart1.setScript(Hibernate.createClob(getSequenceFlowStart().toString()));

		HCustomTask cust1 = new CustomTask();
		service.addObject("cust1", cust1);

		cust1.setDescription("Task Custom 1");
		cust1.setScript(Hibernate.createClob(getCustomScript2().toString()));

		seqStart1.setTask((HTask) cust1);

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
		seqCustom1.setScript(Hibernate.createClob(getSequenceFlowCustomScript1().toString()));
		seqListCustom.add(seqCustom1);

		SequenceFlow seqCustom2 = new SequenceFlow();
		service.addObject("seqCustom2", seqCustom2);

		seqCustom2.setDescription("sequence custom 2");
		seqCustom2.setSequenceNumber(0);
		seqCustom2.setScript(Hibernate.createClob(getSequenceFlowCustomScript2().toString()));
		seqListCustom.add(seqCustom2);

		HFinalTask finalTask = new EndTask();
		service.addObject("finalTask", finalTask);

		finalTask.setDescription("Final Task");
		seqCustom2.setTask((HTask) finalTask);

		cust1.setSequenceFlow(seqListCustom);

		return service;
	}

	private StringBuilder getCustomScript1() {
		StringBuilder sb = new StringBuilder();

		sb.append("import java.lang.String;\n");
		sb.append("String str1 = \"Test String\";\n");
		sb.append("System.out.println(str1);");

		return sb;
	}

	private StringBuilder getCustomScript2() {
		StringBuilder sb = new StringBuilder();

		sb.append("import java.lang.String;\n");
		sb.append("String str1 = \"Test String2\";\n");
		sb.append("System.out.println(str1);");

		return sb;
	}

	private StringBuilder getSequenceFlowStart() {
		StringBuilder sb = new StringBuilder();

		sb.append("return true;");

		return sb;
	}

	private StringBuilder getSequenceFlowCustomScript1() {
		StringBuilder sb = new StringBuilder();

		sb.append("return false;");

		return sb;
	}

	private StringBuilder getSequenceFlowCustomScript2() {
		StringBuilder sb = new StringBuilder();

		sb.append("return true;");

		return sb;
	}

	public ServiceHandler createService() throws Exception {
		ClassWrapper cb = new ClassBuilder();
		cb.setPackage("BNIETax");
		cb.addModifier(Modifier.PUBLIC);
		cb.setName("doPaymentMPNG2");
		cb.addInterface(ServiceHandler.class.getSimpleName());
		cb.setExtend(Service.class.getName());
		cb.addImport(ServiceHandler.class.getPackage().getName() + ".Main." + ServiceHandler.class.getSimpleName());
		cb.addImport(StartTask.class.getName());
		cb.addImport(HSequenceFlow.class.getName());
		cb.addImport(HCustomTask.class.getName());
		cb.addImport(List.class.getName());
		cb.addImport(SequenceFlow.class.getName());
		cb.addImport(CustomTask.class.getName());
		cb.addImport(HTask.class.getName());
		cb.addImport(ArrayList.class.getName());
		cb.addImport(HFinalTask.class.getName());
		cb.addImport(EndTask.class.getName());

		CommonMethodWrapper method = new CommonMethodBuilder(cb);
		method.addModifier(Modifier.PUBLIC);
		method.setReturnType(new VoidReturnType());
		method.setName("executeHandler");

		method.addLineCode("try {");
		method.addLineCode("StartTask start = new StartTask()");
		method.addLineCode("start.setDescription(\"Start\")");
		method.addLineCode("addStartTask(start)");

		method.addLineCode("HSequenceFlow seqStart1 = new SequenceFlow()");
		method.addLineCode("addObject(\"seqStart1\", seqStart1)");

		method.addLineCode("seqStart1.setDescription(\"sequence start 1\")");
		method.addLineCode("seqStart1.setSequenceNumber(0)");
		method.addLineCode("seqStart1.setScript(getSequenceFlowStart().toString())");

		method.addLineCode("HCustomTask cust1 = new CustomTask()");
		method.addLineCode("addObject(\"cust1\", cust1)");

		method.addLineCode("cust1.setDescription(\"Task Custom 1\")");
		method.addLineCode("cust1.setScript(getCustomScript1().toString())");

		method.addLineCode("seqStart1.setTask((HTask) cust1)");

		method.addLineCode("List<HSequenceFlow> seqListStart = new ArrayList<HSequenceFlow>()");
		method.addLineCode("seqListStart.add(seqStart1)");

		method.addLineCode("start.setSequenceFlow(seqListStart)");
		method.addLineCode("List<HSequenceFlow> seqListCustom = new ArrayList<HSequenceFlow>()");

		method.addLineCode("HSequenceFlow seqCustom1 = new SequenceFlow()");
		method.addLineCode("addObject(\"seqCustom1\", seqCustom1)");

		method.addLineCode("seqCustom1.setDescription(\"sequence custom 1\")");
		method.addLineCode("seqCustom1.setSequenceNumber(0)");
		method.addLineCode("seqCustom1.setScript(getSequenceFlowCustomScript1().toString())");
		method.addLineCode("seqListCustom.add(seqCustom1)");

		method.addLineCode("SequenceFlow seqCustom2 = new SequenceFlow()");
		method.addLineCode("addObject(\"seqCustom2\", seqCustom2)");

		method.addLineCode("seqCustom2.setDescription(\"sequence custom 2\")");
		method.addLineCode("seqCustom2.setSequenceNumber(0)");
		method.addLineCode("seqCustom2.setScript(getSequenceFlowCustomScript2().toString())");
		method.addLineCode("seqListCustom.add(seqCustom2)");

		method.addLineCode("HFinalTask finalTask = new EndTask()");
		method.addLineCode("addObject(\"finalTask\", finalTask)");

		method.addLineCode("finalTask.setDescription(\"Final Task\")");
		method.addLineCode("seqCustom2.setTask((HTask) finalTask)");

		method.addLineCode("cust1.setSequenceFlow(seqListCustom)");

		method.addLineCode("System.out.println(\"Running service : " + cb.getName() + ".\")");
		method.addLineCode("super.init()");
		method.addLineCode("super.execute()");
		method.addLineCode("} catch (Exception e) {");
		method.addLineCode("   System.err.println(e)");
		method.addLineCode("}");

		CommonMethodWrapper method2 = new CommonMethodBuilder(cb);
		method2.setName("getCustomScript1");
		method2.addModifier(Modifier.PRIVATE);
		method2.setReturnType(new ClassReturnType(StringBuilder.class.getName()));
		method2.addLineCode("StringBuilder sb = new StringBuilder()");
		method2.addLineCode("sb.append(\"import java.lang.String;\")");
		method2.addLineCode("sb.append(\"String str1 = \\\"Test String\\\";\")");
		method2.addLineCode("sb.append(\"System.out.println(str1);\")");
		method2.addLineCode("return sb");

		CommonMethodWrapper method3 = new CommonMethodBuilder(cb);
		method3.setName("getSequenceFlowStart");
		method3.addModifier(Modifier.PRIVATE);
		method3.setReturnType(new ClassReturnType(StringBuilder.class.getName()));
		method3.addLineCode("StringBuilder sb = new StringBuilder()");
		method3.addLineCode("sb.append(\"return true;\")");
		method3.addLineCode("return sb");

		CommonMethodWrapper method4 = new CommonMethodBuilder(cb);
		method4.setName("getSequenceFlowCustomScript1");
		method4.addModifier(Modifier.PRIVATE);
		method4.setReturnType(new ClassReturnType(StringBuilder.class.getName()));
		method4.addLineCode("StringBuilder sb = new StringBuilder()");
		method4.addLineCode("sb.append(\"return false;\")");
		method4.addLineCode("return sb");

		CommonMethodWrapper method5 = new CommonMethodBuilder(cb);
		method5.setName("getSequenceFlowCustomScript2");
		method5.addModifier(Modifier.PRIVATE);
		method5.setReturnType(new ClassReturnType(StringBuilder.class.getName()));
		method5.addLineCode("StringBuilder sb = new StringBuilder()");
		method5.addLineCode("sb.append(\"return true;\")");
		method5.addLineCode("return sb");

		// System.out.println(cb.toString());

		SimpleCompiler compilerService = new SimpleCompiler();
		compilerService.cook(cb.toString());

		Class<?> clazzService = Class.forName(cb.getClassName(), true, compilerService.getClassLoader());
		ServiceHandler sh = (ServiceHandler) clazzService.newInstance();

		return sh;
	}

	public static void main(String[] args) {
		try {
			long startTime = System.currentTimeMillis();
			Main app = new Main();
			Service service = app.createService1();

			service.init();
			service.execute();

			long endTime = System.currentTimeMillis();
			System.out.println("Service executed in : " + ((double) (endTime - startTime)) / MILISECONDS + " ms.");
			System.out.println();
			System.out.println();

			startTime = System.currentTimeMillis();
			Service service2 = app.createService2();

			service2.init();
			service2.execute();

			endTime = System.currentTimeMillis();

			System.out.println("Service executed in : " + ((double) (endTime - startTime)) / MILISECONDS + " ms.");
			System.out.println();
			System.out.println();

			// ///////////////////////////////////

			ServiceHandler sh = app.createService();
			startTime = System.currentTimeMillis();
			sh.executeHandler();

			endTime = System.currentTimeMillis();
			System.out.println("Service executed in : " + ((double) (endTime - startTime)) / MILISECONDS + " ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public interface ClassHandler {
		public void execute();
	}

	public interface ServiceHandler {
		public void executeHandler() throws Exception;
	}
}
