/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrv.core.hibernate;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author harvan
 */
class DaoTemplate<T> {

	protected HibernateTemplate template;

	public Session getSession() {
		return template.getSessionFactory().getCurrentSession();
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@SuppressWarnings("unchecked")
	public T save(Serializable obj) {
		return (T) template.save(obj);
	}

	public void update(Serializable obj) {
		template.update(obj);
	}

	public void delete(Serializable obj) {
		template.delete(obj);
	}
}
