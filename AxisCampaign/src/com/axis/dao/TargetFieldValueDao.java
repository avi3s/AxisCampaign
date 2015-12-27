package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.TargetFieldValueEntity;
import com.axis.enumeration.Status;

@Repository
public class TargetFieldValueDao extends
		GenericDao<TargetFieldValueEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(TargetFieldValueDao.class);

	public TargetFieldValueDao() {
		super(TargetFieldValueEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * @param campid
	 * @return
	 */
	public List<String> findFieldNames(int roleCampaignId){
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-Start");
		}
		
		String hql = "select f.filedName from TargetFieldEntity f where f.status = :status and f.roleCampaignEntity.roleCampaignId = :campid";
		List<String> targetFieldName = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("campid", roleCampaignId);
			targetFieldName = query.list();
		} catch (Exception e) {
			logger.error("getAllFieldNames-TargetFieldValueDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-End");
		}
		
		return targetFieldName;
	}
	
	/**
	 * 
	 * @param campid
	 * @return
	 */
	public List<Integer> findFieldNameId(int campid){
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-Start");
		}
		
		String hql = "select f.targetFieldId from TargetFieldEntity f where f.status = :status and f.roleCampaignEntity.roleCampaignId = :campid";
		List<Integer> targetFieldNameId = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("campid", campid);
			targetFieldNameId = query.list();
		} catch (Exception e) {
			logger.error("getAllFieldNames-TargetFieldValueDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-End");
		}
		return targetFieldNameId;
	}
	
	
	public List<TargetFieldValueEntity> findAll() {

		if (logger.isDebugEnabled()) {
			logger.debug("get-AllActive-TargetValueByStatus-Start");
		}

		String hql = "from TargetFieldValueEntity f where f.status = :status order by f.createTimeStamp desc ";
		List<TargetFieldValueEntity> targetFieldValue = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			targetFieldValue = query.list();
		} catch (Exception e) {
			logger.error("get-AllActive-TargetValueByStatus-TargetFieldValueDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("get-AllActive-TargetValueByStatus-End");
		}
		return targetFieldValue;
	}
}