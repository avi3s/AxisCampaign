package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.TargetFieldEntity;
import com.axis.enumeration.Status;
import com.axis.model.TargetFieldModel;

@Repository
public class TargetFieldDao extends GenericDao<TargetFieldEntity, Integer> {

	private static final Logger logger = Logger.getLogger(TargetFieldDao.class);

	public TargetFieldDao() {
		super(TargetFieldEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<TargetFieldEntity> findAll() {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveTargetByStatus-Start");
		}

		String hql = "from TargetFieldEntity f where f.status = :status order by f.createTimeStamp desc ";
		List<TargetFieldEntity> targetField = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			targetField = query.list();
		} catch (Exception e) {
			logger.error("getAllActiveTargetByStatus-TargetFieldDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveTargetByStatus-End");
		}
		
		//System.out.println("targetField: "+ targetField);
		
		return targetField;
	}

	
	public void deactivate(int targetFieldValueId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Deactivate TargetField-Start");
		}
		
		String hql = "update TargetFieldValueEntity tfv set tfv.status = :status where tfv.targetFieldEntity.targetFieldId = :id";
		
		try{
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.INACTIVE); // 0 = Inactive User
			query.setParameter("id", targetFieldValueId);
			query.executeUpdate();
			
		} catch(Exception e){
			logger.error("deactivate-TargetFieldDao ->NOT FOUND", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Deactivate TargetField-End");
		}
	}

	
	
	public int getTargetFieldName(int roleId, int campId, String fieldName) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getTargetFieldName-Start");
		}
		
		//String hql = "select count(*) from TargetFieldEntity tf where tf.status = :status and tf.filedName = :fieldName and tf.roleCampaignEntity.roleEntity.roleId = :roleId and tf.roleCampaignEntity.campaignEntity.campaignId = :campId";
		String hql = "select count(*) from TargetFieldEntity tf where tf.status = :status and tf.filedName = :fieldName and tf.roleCampaignEntity.roleEntity.roleId = :roleId and tf.roleCampaignEntity.campaignEntity.campaignId = :campId";
		
		int totalTargetFields=0;
		
		try{
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("fieldName", fieldName);
			query.setParameter("roleId", roleId);
			query.setParameter("campId", campId);
			long count= (Long)query.uniqueResult();
			totalTargetFields = (int) count;
			System.out.println(totalTargetFields+ " No of Target Field");
			
		} catch(Exception e){
			logger.error("getTargetFieldName-TargetFieldDao ->NOT FOUND", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getTargetFieldName-Start");
		}
		
		return totalTargetFields;
	}

	public int checkDuplicateTargetName(TargetFieldEntity targetFieldEntity) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("checkDuplicateTargetName-Start");
		}
		
		//System.out.println("fieldName: "+targetFieldEntity.getFiledName());
		//System.out.println("target Id: "+targetFieldEntity.getTargetFieldId());
		
		String hql = "select count(*) from TargetFieldEntity tf where tf.status = :status and tf.filedName = :fieldName and tf.roleCampaignEntity.roleCampaignId = :roleCampaignId";
		
		int totalTargetFields=0;
		long count=0;
		
		try{
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("fieldName", targetFieldEntity.getFiledName());
			//query.setParameter("targetFieldId", targetFieldId);  //and tf.targetFieldId <> :targetFieldId
			query.setParameter("roleCampaignId", targetFieldEntity.getRoleCampaignEntity().getRoleCampaignId());			
			count= (Long)query.uniqueResult();
			totalTargetFields = (int) count;
			System.out.println(totalTargetFields+ " No of Target Field");
			
		} catch(Exception e){
			logger.error("checkDuplicateTargetName-TargetFieldDao ->NOT FOUND", e);
		}
		return totalTargetFields;
	}
	
	
	public int fetchDuplicateTargetName(TargetFieldEntity targetFieldEntity, int targetFieldId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("checkDuplicateTargetName-Start");
		}
		
		//System.out.println("fieldName: "+targetFieldEntity.getFiledName());
		//System.out.println("target Id: "+targetFieldEntity.getTargetFieldId());
		
		String hql = "select count(*) from TargetFieldEntity tf where tf.status = :status and tf.filedName = :fieldName and tf.targetFieldId <> :targetFieldId and tf.roleCampaignEntity.roleCampaignId = :roleCampaignId";
		
		int totalTargetFields=0;
		long count=0;
		
		try{
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("fieldName", targetFieldEntity.getFiledName());
			query.setParameter("targetFieldId", targetFieldId);  //and tf.targetFieldId <> :targetFieldId
			query.setParameter("roleCampaignId", targetFieldEntity.getRoleCampaignEntity().getRoleCampaignId());			
			count= (Long)query.uniqueResult();
			totalTargetFields = (int) count;
			System.out.println(totalTargetFields+ " No of Target Field");
			
		} catch(Exception e){
			logger.error("checkDuplicateTargetName-TargetFieldDao ->NOT FOUND", e);
		}
		return totalTargetFields;
	}
	
}