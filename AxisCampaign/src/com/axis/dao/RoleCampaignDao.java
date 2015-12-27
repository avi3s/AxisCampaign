package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.CampaignEntity;
import com.axis.entity.RoleCampaignEntity;
import com.axis.entity.RoleEntity;
import com.axis.enumeration.Status;

@Repository
public class RoleCampaignDao extends GenericDao<RoleCampaignEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(RoleCampaignDao.class);

	public RoleCampaignDao() {
		super(RoleCampaignEntity.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<RoleCampaignEntity> findAllActiveCampaignByRole(int roleId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-Start");
		}

		//String hql = "from RoleCampaignEntity rce where rce.roleEntity.id = :roleId ";
		
		String hql = "from RoleCampaignEntity rce where rce.campaignEntity.status = :status and rce.roleEntity.id = :roleId ";

		List<RoleCampaignEntity> roleCampaignEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status",Status.ACTIVE);
			query.setParameter("roleId", roleId); // For Specific Role
			roleCampaignEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByRole-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}
		return roleCampaignEntities;
	}

	@SuppressWarnings("unchecked")
	public RoleCampaignEntity finfIdagainstRoleandCampaign(
			RoleEntity roleEntity, CampaignEntity campaignEntity) {
		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-Start");
		}

		String hql = "from RoleCampaignEntity rce where rce.roleEntity = :roleEntity and rce.campaignEntity = :campaignEntity ";
		RoleCampaignEntity roleCampaignEntity = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("roleEntity", roleEntity); // For Specific Role
			query.setParameter("campaignEntity", campaignEntity);
			roleCampaignEntity = (RoleCampaignEntity) query.uniqueResult();
			System.out.println("Sizes in the rolecampaign entities"
					+ roleCampaignEntity);

		} catch (Exception e) {
			logger.error(
					"finfIdagainstRoleandCampaign-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("finfIdagainstRoleandCampaign-End");
		}
		return roleCampaignEntity;

	}

	public RoleCampaignEntity findRoleCampaign(int roleId, int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findRoleCampaign-Start");
		}

		String hql = "from RoleCampaignEntity rce where rce.roleEntity.roleId = :roleId AND rce.campaignEntity.campaignId = :campaignId ";

		RoleCampaignEntity roleCampaignEntity = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("roleId", roleId); // For Specific Role
			query.setParameter("campaignId", campaignId);
			roleCampaignEntity = (RoleCampaignEntity) query.uniqueResult();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByRole-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}
		return roleCampaignEntity;
	}
	
	public void deleteRoleCampaign(int roleId, int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findRoleCampaign-Start");
		}
		
		//int roleId1 = Integer.parseInt(roleId);

		String hql = "Delete from RoleCampaignEntity rce where rce.roleEntity.roleId = :roleId AND rce.campaignEntity.campaignId = :campaignId ";

		try {
			
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("roleId", roleId); // For Specific Role
		query.setParameter("campaignId", campaignId);
		 
		int result = query.executeUpdate();
		 
		if (result > 0) {
		    System.out.println("Expensive products was removed");
		}
		
		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByRole-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleCampaignEntity> findAllActiveRoleByCampaign(int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-Start");
		}

		String hql = "from RoleCampaignEntity rce where rce.campaignEntity.campaignId = :campaignId  ";

		List<RoleCampaignEntity> roleCampaignEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("campaignId", campaignId);
			roleCampaignEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByRole-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}
		return roleCampaignEntities;
	}
	
	public void insertRoleCampaign(int roleId, int campaignId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findRoleCampaign-Start");
		}
		
		//int roleId1 = Integer.parseInt(roleId);

		String hql = "insert into RoleCampaignEntity rce() where rce.roleEntity.roleId = :roleId AND rce.campaignEntity.campaignId = :campaignId ";

		try {
			
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("roleId", roleId); // For Specific Role
		query.setParameter("campaignId", campaignId);
		 
		int result = query.executeUpdate();
		 
		if (result > 0) {
		    System.out.println("Expensive products was removed");
		}
		
		} catch (Exception e) {
			logger.error(
					"findAllActiveCampaignByRole-RoleCampaignDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveCampaignByRole-End");
		}
	}
}
