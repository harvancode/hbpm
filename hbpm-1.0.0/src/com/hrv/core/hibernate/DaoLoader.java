package com.hrv.core.hibernate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hrv.core.utils.MemoryClassLoader;

public class DaoLoader {
	@Autowired
	private ApplicationContext appContext;
	private MemoryClassLoader memoryClassLoader;
	private HibernateTemplate hibernateTemplate;
	private Map<String, Object> classMap = new HashMap<String, Object>();
	private Map<String, Object> daoMap = new HashMap<String, Object>();

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("rawtypes")
	public void setMemoryClassLoader(MemoryClassLoader memClzLoader) {
		this.memoryClassLoader = memClzLoader;
		File root = new File(
				"D:/harvan/programming/java/eclipsejee/theapp/src/");
		// File sourceFile = new File(root,
		// "dao/generated/UserGeneratedDaoImpl.java");
		File sourceDir = new File(root, "dao/generated/");

		List<String> classList = memoryClassLoader.run(sourceDir);

		try {
			for (String className : (List<String>) classList) {
				ClassLoader classLoader = Thread.currentThread()
						.getContextClassLoader();

				// URL[] urls = ((URLClassLoader) classLoader).getURLs();
				Class<?> clazz = Class.forName(className, true, classLoader); // Should
				Object obj = clazz.newInstance();
				GeneratedGenericDao dao = null;

				if (obj instanceof GeneratedGenericDao) {
					dao = (GeneratedGenericDao) obj;
					try {
						if (appContext.getBean(dao.getBeanId()) != null) {
							throw new Exception("Failed to register bean : "
									+ dao.getBeanId()
									+ ". Bean is already exist.");
						}
					} catch (BeansException be) {
					}

					classMap.put(clazz.getSimpleName(), obj);

					Method m = clazz.getMethod("setTemplate",
							hibernateTemplate.getClass());
					m.invoke(obj, hibernateTemplate);

					daoMap.put(((GeneratedGenericDao) obj).getBeanId(), obj);
				}
			}

			System.out.println("initialization Dao Loader success...");
			System.out.println("found " + daoMap.size()
					+ " GeneratedGenericDao.");
		} catch (Exception e) {
			System.out.println("initialization Dao Loader failed...");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public GenericDao getDao(String beanId) {
		return (GenericDao) daoMap.get(beanId);
	}
}