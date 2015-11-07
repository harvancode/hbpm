package com.hrv.springmvc.sequenceflow.service.impl;

import java.util.List;

import com.hrv.core.hibernate.GenericDao;
import com.hrv.hbpm.core.SequenceFlow;
import com.hrv.springmvc.sequenceflow.service.SequenceFlowDao;

@SuppressWarnings("unchecked")
public class SequenceFlowDaoImpl extends GenericDao<SequenceFlow> implements SequenceFlowDao {

	@Override
	public List<SequenceFlow> getSequenceFlowByParentTaskId(String parentTaskId) {
		return getSession().createQuery(new StringBuffer("FROM com.hrv.hbpm.core.SequenceFlow WHERE parentTask.id =:parentTaskId ORDER BY sequenceNumber ASC").toString())
				.setString("parentTaskId", parentTaskId).list();
	}
}