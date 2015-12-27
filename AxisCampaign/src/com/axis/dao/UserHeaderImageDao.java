package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.UserHeaderImageEntity;
import com.axis.enumeration.Status;

@Repository
public class UserHeaderImageDao extends
		GenericDao<UserHeaderImageEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(UserHeaderImageDao.class);

	public UserHeaderImageDao() {
		super(UserHeaderImageEntity.class);
		// TODO Auto-generated constructor stub
	}

	public List<UserHeaderImageEntity> findAllActiveUserHeaderImage() {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserHeaderImage-Start");
		}

		List<UserHeaderImageEntity> userHeaderImageEntities = null;

		String hql = "from UserHeaderImageEntity u where u.status = :status order by u.createTimeStamp desc ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			userHeaderImageEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveUserHeaderImage-UserHeaderImageDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserHeaderImage-End");
		}

		return userHeaderImageEntities;
	}
}
