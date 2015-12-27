package com.axis.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.CampaignFileEntity;
import com.axis.enumeration.Status;

@Repository
public class CampaignFileDao extends GenericDao<CampaignFileEntity, Integer>{

	private static final Logger logger = Logger.getLogger(CampaignFileDao.class);

	public CampaignFileDao() {
		super(CampaignFileEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	public int campaignFileDelete(int campaign_file_id) {

		if (logger.isDebugEnabled()) {
			logger.debug("campaignFileDelete-Start");
		}
		int result = 0;
		try {
			String hql = "DELETE FROM CampaignFileEntity cf WHERE cf.campaignFileId = :campaignFileId ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("campaignFileId", campaign_file_id);
			result = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("campaignFileDelete-AdminCampaignFileDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("campaignFileDelete-Start");
		}
		return result;
	}
	
	public int campaignFileDeleteByCampaignId(int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("campaignFileDelete-Start");
		}
		int result = 0;
		try {
			String hql = "update CampaignFileEntity cf set cf.status = :status WHERE cf.campaignEntity.campaignId = :campaignId ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.INACTIVE);
			query.setParameter("campaignId", campaignId);
			result = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("campaignFileDelete-AdminCampaignFileDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("campaignFileDelete-Start");
		}
		return result;
	}
}
