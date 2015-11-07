/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.hibernate;

/**
 *
 * @author harvan
 */
public abstract class GeneratedGenericDao<T> extends GenericDao<T> {
	public abstract String getBeanId();
}