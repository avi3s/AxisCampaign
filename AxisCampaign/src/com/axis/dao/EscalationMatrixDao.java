package com.axis.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.axis.entity.CampaignEntity;
import com.axis.entity.EscalationMatrixEntity;
import com.axis.enumeration.Status;

@Repository
public class EscalationMatrixDao extends
		GenericDao<EscalationMatrixEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(EscalationMatrixDao.class);

	public EscalationMatrixDao() {
		super(EscalationMatrixEntity.class);

	}

	@SuppressWarnings("unchecked")
	public List<EscalationMatrixEntity> findAllActiveEscalationMatrixByStatus() {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveEscalationMatrixByStatus-Start");
		}

		String hql = "from EscalationMatrixEntity e where e.status = :status order by e.createTimeStamp desc ";
		List<EscalationMatrixEntity> escalationMatrixEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			escalationMatrixEntities = query.list();
			for (EscalationMatrixEntity entity : escalationMatrixEntities) {

				System.out
						.println("entity.getCampaignEntity().getCampaignName() dao"
								+ entity.getCampaignEntity().getCampaignName());
			}

		} catch (Exception e) {
			logger.error(
					"findAllActiveEscalationMatrixByStatus-EscalationMatrixDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveEscalationMatrixByStatus-End");
		}
		return escalationMatrixEntities;
	}

	public EscalationMatrixEntity findByContactNumber(String contactNumber) {

		if (logger.isDebugEnabled()) {
			logger.debug("findByContactNumber(String contactNumber)-Start ");
		}

		String hql = "from EscalationMatrixEntity e where e.contactNumber = :contactNumber";

		EscalationMatrixEntity escalationMatrixEntity = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("contactNumber", contactNumber);

			// logger.info(escalationMatrixEntity.getContactNumber());

			List<EscalationMatrixEntity> escalationMatrixEntity1 = query.list();
			if (escalationMatrixEntity1 != null) {
				escalationMatrixEntity = escalationMatrixEntity1.get(0);
			}

			// logger.info(escalationMatrixEntity.getContactNumber());

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findByContactNumber(String contactNumber)-End ");
		}

		return escalationMatrixEntity;

	}

	public EscalationMatrixEntity findByEmailId(String email) {

		if (logger.isDebugEnabled()) {
			logger.debug("findByEmailId(String email)-Start ");
		}

		String hql = "from EscalationMatrixEntity e where e.email = :email";

		EscalationMatrixEntity escalationMatrixEntity = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("email", email);

			List<EscalationMatrixEntity> escalationMatrixEntity1 = query.list();
			if (escalationMatrixEntity1 != null) {
				escalationMatrixEntity = escalationMatrixEntity1.get(0);
				logger.info(escalationMatrixEntity.getEmail());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findByEmailId(String email)-End ");
		}

		return escalationMatrixEntity;

	}

	public boolean findByEmailAndId(String email, int id) {

		if (logger.isDebugEnabled()) {
			logger.debug(" findByEmailAndId(String email,int id) -> Start ");
		}

		String hql = "from EscalationMatrixEntity e where e.status = :status and e.email =:email and e.id not in (:id)";

		int i = 0;

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("email", email);
			query.setParameter("id", id);

			i = query.list().size();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findByEmailAndId(String email,int id) -> End ");
		}

		return i > 0;

	}

	public boolean findByContactAndId(String contactNumber, int id) {

		if (logger.isDebugEnabled()) {
			logger.debug(" findByContactAndId(String contactNumber,int id) -> Start ");
		}

		String hql = "from EscalationMatrixEntity e where e.status = :status and e.contactNumber =:contactNumber and e.id not in (:id)";

		int i = 0;

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("contactNumber", contactNumber);
			query.setParameter("id", id);

			i = query.list().size();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findByContactAndId(String contactNumber,int id) -> End ");
		}

		return i > 0;

	}

	public List<Integer> findEscalationNameId(int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-Start");
		}

		String hql = "select from EscalationMatrixEntity e where e.status = :status and e.campaign.Id = :campaignId";
		List<Integer> escalationNameId = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("campid", campaignId);
			escalationNameId = query.list();
		} catch (Exception e) {
			logger.error("getAllFieldNames-TargetFieldValueDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllFieldNames-End");
		}
		return escalationNameId;
	}

	/*
	 * public boolean isContactNumberExists(String contactNumber) { Criteria
	 * criteria =
	 * sessionFactory.getCurrentSession().createCriteria(entityClass);
	 * criteria.add(Restrictions.eq("contactNumber", contactNumber)); List list
	 * = criteria.list();
	 * 
	 * if(list == null || list.isEmpty()){ return false; } else { return true; }
	 * }
	 * 
	 * public boolean isEmailExists(String email) { Criteria criteria =
	 * sessionFactory.getCurrentSession().createCriteria(entityClass);
	 * criteria.add(Restrictions.eq("email", email)); List list =
	 * criteria.list();
	 * 
	 * if(list == null || list.isEmpty()){ return false; } else { return true; }
	 * }
	 */

	/*
	 * public static boolean emailValidate(String h){ Pattern pattern; Matcher
	 * matcher;
	 * 
	 * String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
	 * "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 * pattern=Pattern.compile(EMAIL_PATTERN); matcher=pattern.matcher(h);
	 * return matcher.matches(); }
	 * 
	 * 
	 * public static boolean mNoValidate(String h){ Pattern pattern; Matcher
	 * matcher;
	 * 
	 * String Mno_PATTERN = "\\d{10}"; pattern=Pattern.compile(Mno_PATTERN);
	 * matcher=pattern.matcher(h); return matcher.matches(); }
	 */

	public boolean isContactNumberExists(String contactNumber) {

		if (logger.isDebugEnabled()) {
			logger.debug("getContactNumber(String ContactNumber)-Start");
		}

		String hql = "from EscalationMatrixEntity r where r.contactNumber=:contactNumber";

		int i = 0;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("contactNumber", contactNumber); // 1 = Active
																// User

			i = query.list().size();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(
					"getContactNumber - EscalationMatrixEntity ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getContactNumber(String ContactNumber)-End");
		}

		return i > 0;

	}

	public boolean isEmailExists(String email) {
		if (logger.isDebugEnabled()) {
			logger.debug("getEmail(String email)-Start");
		}

		String hql = "from EscalationMatrixEntity r where r.email=:email";

		int i = 0;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("email", email); // 1 = Active User

			i = query.list().size();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("getEmail - EscalationMatrixEntity ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getEmail(String email)-End");
		}

		return i > 0;

	}
	
	
	public List<EscalationMatrixEntity> findEscalationMatirx(int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findEscalationMatirx-Start");
		}

		String hql = "from EscalationMatrixEntity e where e.status = :status and e.campaignEntity.campaignId = :campaignId";
		List<EscalationMatrixEntity> escalationMatrixEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("campaignId", campaignId);
			escalationMatrixEntities = query.list();
			
		} catch (Exception e) {
			logger.error("findEscalationMatirx-TargetFieldValueDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findEscalationMatirx-End");
		}
		return escalationMatrixEntities;
	}

}