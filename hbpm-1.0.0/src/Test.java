import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrv.hbpm.core.SequenceFlow;
import com.hrv.hbpm.process.HSequenceFlow;

public class Test {

	public void test1() {
		List<SequenceFlow> list = new ArrayList<SequenceFlow>();
		SequenceFlow seqFlow = new SequenceFlow();
		list.add(seqFlow);

		HSequenceFlow seqFlowInf = seqFlow;

	}

	public void test2() {
		test21("serviceName", "test22", "param1", 2);

	}

	public void test22(String param1, Integer param2) {

	}

	private void test21(String serviceName, String methodName, Object... parameters) {
		Map map = new HashMap();
		StringBuffer buff = new StringBuffer();

		Method method = null;
		try {
			if (parameters == null) {
				method = this.getClass().getMethod(methodName, new Class[] {});
			} else {
				Class[] params = new Class[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					params[i] = parameters[i].getClass();
					buff.append(parameters[i].getClass().getSimpleName());
				}

				method = this.getClass().getMethod(methodName, params);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		map.put(serviceName + methodName + buff.toString(), method);

		System.out.println(map);
	}

	private void test3() {
		long start, end;

		start = System.nanoTime();

		try {
			Thread.sleep(1234);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		end = System.nanoTime();

		System.out.println("Executed in : " + ((end - start) / 1000000d));
	}

	public static void main(String[] args) {
		Test app = new Test();

		// app.test1();
		// app.test2();
		app.test3();
	}
}