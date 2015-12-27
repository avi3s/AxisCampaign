package com.axis.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.axis.common.MessageUtil;
import com.axis.entity.AchievementFieldEntity;
import com.axis.entity.AchievementFieldValueEntity;
import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.enumeration.Status;
import com.axis.model.AcheivementModel;

@Repository
public class AchievementFieldDao extends
		GenericDao<AchievementFieldEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(AchievementFieldDao.class);

	public AchievementFieldDao() {
		super(AchievementFieldEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private MessageUtil messageUtil;

	/*
	 * .............................. General Functions For Fetching Employee
	 * Number, Sol ID and Ranking ---------------------------
	 */

	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> findCommonFieldName() {

		if (logger.isDebugEnabled()) {
			logger.debug("findCommonFieldName-Start");
		}

		String hql = "from AchievementFieldEntity afe where afe.filedName = :employeeNumber or afe.filedName = :SolId or afe.filedName = :Ranking";

		List<AchievementFieldEntity> achievementFieldEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("employeeNumber" ,messageUtil.getBundle("emp.no") ); 
			query.setParameter("SolId", messageUtil.getBundle("emp.ranking") ); 
			query.setParameter("Ranking", messageUtil.getBundle("emp.solId") ); 
			
			achievementFieldEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findCommonFieldName-End");
		}

		return achievementFieldEntities;

	}

	/*
	 * ..............................Find All Values Against Role and
	 * Campaign..........................
	 */

	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> findAllAgainstRoleCampaign(
			RoleCampaignEntity roleCampaignEntity) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}

		String hql = "from AchievementFieldEntity afe where afe.roleCampaignEntity = :roleCampaignEntity";

		List<AchievementFieldEntity> achievementFieldEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("roleCampaignEntity", roleCampaignEntity); // For
																			// Specific
																			// Role

			achievementFieldEntities = query.list();
			
			achievementFieldEntities.addAll(this.findCommonFieldName());

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}

		List<String> string = null;

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldEntities;

	}

	// ....................................END...........................................//

	/*
	 * ........................Find All Values Against
	 * Status............................
	 */
	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> findAllValuesAgainstStatus() {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}

		String hql = "from AchievementFieldEntity afe where afe.status = :status order by afe.achievementFieldId desc";
		List<AchievementFieldEntity> achievementFieldEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // For Specific Role

			achievementFieldEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldEntities;

	}

	// ....................................(End of Values Against
	// Status)......................................//

	// ....................................(For Checking Duplicate
	// Value)........................//

	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> getDuplicateValueCheck(
			AcheivementModel acheivementModel) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}
		// System.out.println("Acheivement details are ::::"+);
		String hql = "from AchievementFieldEntity afve where afve.filedName = :filedName and afve.roleCampaignEntity = :roleCampaignEntity";
		List<AchievementFieldEntity> achievementFieldValueEntity = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("filedName", acheivementModel.getFieldName()); // For
																				// Specific
																				// Role
			query.setParameter("roleCampaignEntity",
					acheivementModel.getRoleCampaignEntity());
			achievementFieldValueEntity = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}
		List<String> string = null;

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldValueEntity;

	}

	// ................................. END of Duplicate Value
	// Check..........................................//

	/* ...................................For Route 1......................... */

	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> findAllAgainstRoleCampaignFirstType(
			RoleCampaignEntity roleCampaignEntity) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}

		String fieldType = "Route1";

		String hql = "from AchievementFieldEntity afe where afe.roleCampaignEntity = :roleCampaignEntity and afe.fieldType = :fieldType";
		List<AchievementFieldEntity> achievementFieldEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("roleCampaignEntity", roleCampaignEntity); // For
																			// Specific
																			// Role
			query.setParameter("fieldType", fieldType);
			achievementFieldEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}
		List<String> string = null;

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldEntities;

	}

	// ......................................END of Route
	// 1.....................................//

	/* ...................................For Route 2......................... */
	@SuppressWarnings("unchecked")
	public List<AchievementFieldEntity> findAllAgainstRoleCampaignSecondType(
			RoleCampaignEntity roleCampaignEntity) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllAgainstRoleCampaign-Start");
		}

		String fieldType = "Route2";

		String hql = "from AchievementFieldEntity afe where afe.roleCampaignEntity = :roleCampaignEntity  and afe.fieldType = :fieldType";
		List<AchievementFieldEntity> achievementFieldEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("roleCampaignEntity", roleCampaignEntity); // For
																			// Specific
																			// Role
			query.setParameter("fieldType", fieldType);
			achievementFieldEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}
		List<String> string = null;

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return achievementFieldEntities;

	}

}