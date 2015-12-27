package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.RoleLevelEntity;
import com.axis.entity.UserEntity;
import com.axis.enumeration.Status;
import com.axis.model.CampaignModel;
import com.axis.model.UserModel;

@Repository
public class UserDao extends GenericDao<UserEntity, Integer> {

	private static final Logger logger = Logger.getLogger(UserDao.class);

	public UserDao() {
		super(UserEntity.class);
		// TODO Auto-generated constructor stub
	}

	public List<UserEntity> getUserListBySubRoleLevelId(Integer parentId) {

		List<UserEntity> userEntities = null;

		String hql = "from UserEntity ue where ue.subRoleLevelEntity.subRoleLevelId="
				+ parentId;
		System.out.println("*********** mahan query HQL form :: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			userEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userEntities;
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> findAllActiveUserByStatus() {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByStatus-Start");
		}

		String hql = "from UserEntity u where u.status = :status and u.userId != u.userEntity.userId order by u.createTimeStamp desc ";
		List<UserEntity> userEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			System.out
					.println("sad*****************" + Status.ACTIVE.ordinal());
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			userEntities = query.list();

		} catch (Exception e) {
			logger.error("findAllActiveUserByStatus-UserDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByStatus-End");
		}
		return userEntities;
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> findAllActiveUserByRole(int roleId) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByRole-Start");
		}

		String hql = "from UserEntity u where u.roleEntity.roleId = :roleId and u.status = :status order by u.createTimeStamp desc ";
		List<UserEntity> userEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("roleId", roleId); // By Role
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			userEntities = query.list();

		} catch (Exception e) {
			logger.error("findAllActiveUserByRole-UserDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByRole-End");
		}
		return userEntities;
	}

	public List<UserEntity> adminLoginCheck(UserModel userModel) {

		String hql = "from UserEntity u where u.userName= :userName and u.password= :password and status= :status";
		List<UserEntity> userEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("userName", userModel.getUserName());
			query.setParameter("password", userModel.getPassword());
			query.setParameter("status", Status.ACTIVE);
			userEntities = query.list();

		} catch (Exception e) {
			System.out.println("no user found");
			logger.error("findAllActiveUserByRole-UserDao ->NOT FOUND", e);
		}
		return userEntities;

	}

	public List<UserEntity> userLoginCheck(UserModel userModel) {

		String hql = "from UserEntity u where u.userName= :userName and u.password= :password and status= :status";
		List<UserEntity> userEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("userName", userModel.getUserName());
			query.setParameter("password", userModel.getPassword());
			query.setParameter("status", Status.ACTIVE);
			userEntities = query.list();

		} catch (Exception e) {
			System.out.println("no user found");
			logger.error("findAllActiveUserByRole-UserDao ->NOT FOUND", e);
		}
		return userEntities;
	}

	public List<UserEntity> findOtherActiveUserByStatus(UserModel userModel) {
		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByStatus-Start");
		}

		String hql = "from UserEntity u where u.status = :status and u.userId != :userId order by u.createTimeStamp desc ";
		List<UserEntity> userEntities = null;

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			System.out
					.println("sad*****************" + Status.ACTIVE.ordinal());
			query.setParameter("userId", userModel.getUserId());
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			userEntities = query.list();

		} catch (Exception e) {
			logger.error("findAllActiveUserByStatus-UserDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserByStatus-End");
		}
		return userEntities;
	}

	
}