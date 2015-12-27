package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.UserFileUploadEntity;
import com.axis.entity.UserHeaderImageEntity;
import com.axis.enumeration.Status;

@Repository
public class UserFileUploadDao extends GenericDao<UserFileUploadEntity, Integer> {

	private static final Logger logger = Logger.getLogger(UserFileUploadDao.class);

	public UserFileUploadDao() {
		super(UserFileUploadEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<UserFileUploadEntity> findAllActiveUserFile(int createdBy) {

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserFile-Start");
		}

		List<UserFileUploadEntity> userFileUploadEntities = null;

		String hql = "from UserFileUploadEntity u where u.status = :status and u.createdBy =:createdBy order by u.createTimeStamp desc ";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("status", Status.ACTIVE); // 1 = Active User
			query.setParameter("createdBy", createdBy); // 1 = Active User
			userFileUploadEntities = query.list();

		} catch (Exception e) {
			logger.error(
					"findAllActiveUserFile-UserFileUploadDao ->NOT FOUND",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findAllActiveUserFile-End");
		}

		return userFileUploadEntities;
	}
}
