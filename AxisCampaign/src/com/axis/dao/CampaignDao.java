package com.axis.dao;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Query;

import com.axis.entity.CampaignEntity;
import com.axis.enumeration.State;
import com.axis.enumeration.Status;
import com.axis.model.CampaignModel;

@Repository
public class CampaignDao extends GenericDao<CampaignEntity, Integer> {

	private static final Logger logger = Logger.getLogger(CampaignDao.class);

	public CampaignDao() {
		super(CampaignEntity.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<CampaignEntity> findAllActiveCampaignByStatus() {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByStatus-Start");
		}

		String hql = "from CampaignEntity c where c.status = :status order by c.createTimeStamp desc ";
		List<CampaignEntity> campaignEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			campaignEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByStatus-CampaignDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByStatus-End");
		}
		return campaignEntities;
	}

	@Transactional
	public boolean findAllActiveCampaignByCampaignName(
			CampaignModel campaignModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByCampaignName-Start");
		}

		int i = 0;

		String hql = "from CampaignEntity ce where ce.campaignName = :campaignName and ce.financialYear =:financialYear and ce.quarterId =:quarterId and ce.status =:status";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("campaignName", campaignModel.getCampaignName()); // Campaign
																					// Name
																					// Exists
																					// or
																					// not
			query.setParameter("financialYear",
					campaignModel.getFinancialYear());
			query.setParameter("quarterId", campaignModel.getQuarterId());
			query.setParameter("status", Status.ACTIVE); // 1 = Active User

			i = query.list().size();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByCampaignName-CampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByCampaignName-End");
		}
		return i > 0;
	}

	@Transactional
	public boolean findAllActiveCampaignByOtherCampaignName(
			CampaignModel campaignModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByCampaignName-Start");
		}

		int i = 0;

		String hql = "from CampaignEntity ce where ce.campaignName = :campaignName and ce.financialYear =:financialYear and ce.quarterId =:quarterId and ce.status =:status and ce.campaignId not in (:campaignId)";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("campaignName", campaignModel.getCampaignName()); // Campaign
																					// Name
																					// Exists
																					// or
																					// not
			query.setParameter("financialYear",
					campaignModel.getFinancialYear());
			query.setParameter("quarterId", campaignModel.getQuarterId());
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("campaignId", campaignModel.getCampaignId());

			i = query.list().size();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByCampaignName-CampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByCampaignName-End");
		}
		return i > 0;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public List<CampaignEntity> getActiveStateCampaignList() {

		if (logger.isDebugEnabled()) {
			logger.debug("getActiveStateCampaignList-Start");

		}
		List<CampaignEntity> campaignEntities = null;
		/*
		 * ApplicationContext applicationContext = new
		 * ClassPathXmlApplicationContext("classpath*:*spring-servlet.xml");
		 * 
		 * sessionFactory = (SessionFactory)
		 * applicationContext.getBean("sessionFactory");
		 */
		String hql = "FROM CampaignEntity c  where c.status=:status AND :currentDate BETWEEN c.quarterStartDate AND c.quarterEndDate";
		// String hql ="FROM CampaignEntity c  where c.status=:status ";
		try {
			Calendar date = new GregorianCalendar();
			// reset hour, minutes, seconds and millis
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			/* query.setParameter("inactiveStatus", Status.INACTIVE); */
			/* query.setParameter("activeState", Status.ACTIVE); */
			query.setDate("currentDate", new Date());
			campaignEntities = query.list();

		} catch (Exception e) {
			logger.error("getActiveStateCampaignList-CampaignDao ->NOT FOUND",
					e);
		}

		return campaignEntities;

	}

	@Transactional
	public void setCampaignStateActive(CampaignEntity campaignEntity) {

		if (logger.isDebugEnabled()) {
			logger.debug("setCampaignStateActive-Start");
		}

		String hql = "Update CampaignEntity c set c.state =:state WHERE c.id=:id ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("state", Status.ACTIVE);
			query.setParameter("id", campaignEntity.getCampaignId());
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("setCampaignStateActive-CampaignDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getActiveStateCampaignList-End");
		}
	}

	@Transactional
	public void setCampaignStateInActive(CampaignEntity campaignEntity) {

		if (logger.isDebugEnabled()) {
			logger.debug("setCampaignStateActive-Start");
		}

		String hql = "Update CampaignEntity c set c.state =:state WHERE c.id=:id ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("state", State.INACTIVE);
			query.setParameter("id", campaignEntity.getCampaignId());
			query.executeUpdate();

		} catch (Exception e) {
			logger.error("setCampaignStateActive-CampaignDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getActiveStateCampaignList-End");
		}
	}

	@Transactional
	public List<CampaignEntity> findAll() {

		String hql = "FROM CampaignEntity c where c.status=:status";
		List<CampaignEntity> campaignEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", State.ACTIVE);
			campaignEntities = query.list();

		} catch (Exception e) {
			logger.error("setCampaignStateActive-CampaignDao ->NOT FOUND", e);
		}
		return campaignEntities;
	}
	
	@Transactional
	public CampaignEntity fetchCampaignById (int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignById-Start");
		}

		CampaignEntity campaignEntity = null;
		String hql = "FROM CampaignEntity c where c.state=:state and c.id=:id";
		
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("state", State.ACTIVE);
			query.setParameter("id", campaignId);
			campaignEntity = (CampaignEntity) query.uniqueResult();

		} catch (Exception e) {
			logger.error("fetchCampaignById-CampaignDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("fetchCampaignById-End");
		}
		
		return campaignEntity;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
}