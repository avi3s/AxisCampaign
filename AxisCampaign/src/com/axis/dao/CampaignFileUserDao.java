package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.CampaignFileUserEntity;
import com.axis.enumeration.Status;

@Repository
public class CampaignFileUserDao extends GenericDao<CampaignFileUserEntity, Integer> {

	private static final Logger logger = Logger.getLogger(CampaignFileUserDao.class);

	public CampaignFileUserDao() {
		super(CampaignFileUserEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<CampaignFileUserEntity> findAllActiveCampaignFileUserByStatus() {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignFileUserByStatus-Start");
		}

		String hql = "from CampaignFileUserEntity cfue where cfue.status = :status order by cfue.createTimeStamp desc ";
		List<CampaignFileUserEntity> campaignFileUserEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			campaignFileUserEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignFileUserByStatus-CampaignFileUserDao ->NOT FOUND", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignFileUserByStatus-End");
		}
		return campaignFileUserEntities;
	}
	
	@SuppressWarnings("unchecked")
	public CampaignFileUserEntity findAllActiveCampaignFileUserByFileName(String fileName) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignFileUserByFileName-Start");
		}

		String hql = "from CampaignFileUserEntity cfue where cfue.fileName = :fileName ";
		CampaignFileUserEntity campaignFileUserEntity = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("fileName", fileName); // FileName Exists or not
			campaignFileUserEntity = (CampaignFileUserEntity) query.uniqueResult();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignFileUserByFileName-CampaignFileUserDao ->NOT FOUND", e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignFileUserByFileName-End");
		}
		return campaignFileUserEntity;
	}
}
