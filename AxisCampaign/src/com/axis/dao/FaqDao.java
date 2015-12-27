package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.CampaignEntity;
import com.axis.entity.FaqEntity;
import com.axis.enumeration.Status;

@Repository
public class FaqDao extends GenericDao<FaqEntity, Integer> {

	private static final Logger logger = Logger.getLogger(FaqDao.class);

	public FaqDao() {
		super(FaqEntity.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * public List<CampaignEntity> findAllActiveCampaignByStatus() {
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("findAllActiveCampaignByStatus-Start"); }
	 * 
	 * String hql =
	 * "from CampaignEntity c where c.status = :status order by c.createTimeStamp desc "
	 * ; List<CampaignEntity> campaignEntities = null;
	 * 
	 * try { Query query = sessionFactory.getCurrentSession().createQuery(hql);
	 * query.setParameter("status", Status.ACTIVE); // 1 = Active User
	 * campaignEntities = query.list();
	 * 
	 * } catch (Exception e) { logger.error(
	 * "findAllActiveCampaignByStatus-CampaignDao ->NOT FOUND", e); }
	 * 
	 * if (logger.isDebugEnabled()) {
	 * logger.debug("findAllActiveCampaignByStatus-End"); } return
	 * campaignEntities; }
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<FaqEntity> getAllFaqsfromDB() {
		// TODO Auto-generated method stub
		String hql = "from FaqEntity faq where faq.status =:status order by faq.createTimeStamp desc";
		List<FaqEntity> faqEntityList = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE);
			faqEntityList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faqEntityList;
	}

	public FaqEntity getFaqsfromDBById(int id) {
		// TODO Auto-generated method stub
		String hql = "from FaqEntity f where f.faqId=:faqid";
		FaqEntity faqEntity = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("faqid", id);
			faqEntity = (FaqEntity) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faqEntity;
	}

	/*********************************************** User Module For FAQ ******************************************************/

	@SuppressWarnings("unchecked")
	public List<FaqEntity> fetchFAQForUserByCampaignId(int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchFAQForUserByCampaignId - Start");
		}

		String hql = "from FaqEntity faq where faq.status =:status and faq.campaignEntity.campaignId =:campaignId order by faq.createTimeStamp desc";

		List<FaqEntity> faqEntityList = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("campaignId", campaignId);
			query.setParameter("status", Status.ACTIVE);
			
			faqEntityList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("fetchFAQForUserByCampaignId - End");
		}

		return faqEntityList;
	}

}