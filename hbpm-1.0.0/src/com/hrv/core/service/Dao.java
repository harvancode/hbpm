/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author harvan
 */
public interface Dao<T> {

	public T get(Serializable id);

	public Object get(Class<?> clazz, Serializable id);

	public List<T> getAll();

	public T save(Serializable obj);

	public void update(Serializable obj);

	public void delete(Serializable obj);
}