/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.hrv.core.service.Dao;

/**
 * 
 * @author harvan
 */
public class GenericDao<T> extends DaoTemplate<T> implements Dao<T> {

	@SuppressWarnings("unchecked")
	private Class<T> entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	public T get(Serializable id) {
		return template.get(entity, id);
	}

	public Object get(Class<?> clazz, Serializable id) {
		return template.get(clazz, id);
	}

	public List<T> getAll() {
		return template.loadAll(entity);
	}
}