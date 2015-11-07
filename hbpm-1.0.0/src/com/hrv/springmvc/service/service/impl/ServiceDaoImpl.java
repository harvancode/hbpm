package com.hrv.springmvc.service.service.impl;

import java.io.Serializable;
import java.util.List;

import com.hrv.core.hibernate.GenericDao;
import com.hrv.hbpm.core.Service;
import com.hrv.springmvc.service.service.ServiceDao;

@SuppressWarnings("unchecked")
public class ServiceDaoImpl extends GenericDao<Service> implements ServiceDao {

	@Override
	public Service get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Service> getAll() {
		return getSession().createQuery("FROM com.hrv.hbpm.core.Service").list();
	}

	@Override
	public Service save(Serializable obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Serializable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Serializable obj) {
		// TODO Auto-generated method stub

	}

}