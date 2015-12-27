package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.RoleEntity;
import com.axis.entity.RoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.model.RoleModel;

@Repository
public class RoleDao extends GenericDao<RoleEntity, Integer> {

	private static final Logger logger = Logger.getLogger(RoleDao.class);

	public RoleDao() {
		super(RoleEntity.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<RoleEntity> getAllActiveRoleByStatus() {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoleByStatus-Start");
		}

		String hql = "from RoleEntity r where r.status = :status and r.roleName not in (:roleName) order by r.createTimeStamp desc";

		List<RoleEntity> roleEntities = null;

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("roleName", "ADMIN");

			roleEntities = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getAllActiveRoleByStatus-RoleDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveRoleByStatus-Start");
		}

		return roleEntities;

	}

	public List<RoleEntity> findOthers(RoleModel roleModel) {
		List<RoleEntity> roleEntities = null;

		String hql = "from RoleEntity re where re.status= :status and re.roleId != :roleId";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("roleId", roleModel.getRoleId());
			query.setParameter("status", Status.ACTIVE);
			roleEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleEntities;
	}

	/**
	 * this method is use to get admin all detail
	 * 
	 * @return User user
	 */
	public RoleEntity getAdminDetails() {

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminDetails - Start");
		}

		RoleEntity role = null;
		String hql = "from RoleEntity r where r.roleName=:roleName";

		try {

			Query query = sessionFactory.openSession().createQuery(hql);

			query.setParameter("roleName", "ADMIN");

			role = (RoleEntity) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("getAdminDetails - RoleDao ->NOT FOUND", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAdminDetails - End");
		}

		return role;

	}

}