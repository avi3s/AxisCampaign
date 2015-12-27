package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.TargetFieldEntity;
import com.axis.entity.TargetFieldValueEntity;
import com.axis.enumeration.Status;

@Repository
public class UserTargetDao extends GenericDao<TargetFieldEntity, Integer>{
	
	private static final Logger logger = Logger
			.getLogger(UserTargetDao.class);

	public UserTargetDao() {
		super(TargetFieldEntity.class);
		// TODO Auto-generated constructor stub
	}

/* ..............................Find All Values Against Role and Campaign..........................*/
	
	@SuppressWarnings("unchecked")
	public List<TargetFieldEntity> findAllAgainstRoleCampaign(RoleCampaignEntity roleCampaignEntity){
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		
		String hql = "from TargetFieldEntity tfe where tfe.status = :status and tfe.roleCampaignEntity = :roleCampaignEntity" ;
		
		List<TargetFieldEntity> targetFieldEntities = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("roleCampaignEntity", roleCampaignEntity); // For Specific Role
			
			targetFieldEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND", e);
		}
		
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return targetFieldEntities;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TargetFieldValueEntity> findAllAgainstTargetId(TargetFieldEntity targetFieldEntity){
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstTargetId-Start");
		}
		
		String hql = "from TargetFieldValueEntity afve where afve.status =:status and afve.targetFieldEntity = :targetFieldEntity" ;
		List<TargetFieldValueEntity> targetFieldValueEntities = null;
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE);
			query.setParameter("targetFieldEntity", targetFieldEntity); // For Specific Acheivement Field Entity
			
			targetFieldValueEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"fieldValueAgainstTargetFieldName ->NOT FOUND", e);
		}
		List<String> string=null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstTargetId-End");
		}
		return targetFieldValueEntities;
		
	}
}
