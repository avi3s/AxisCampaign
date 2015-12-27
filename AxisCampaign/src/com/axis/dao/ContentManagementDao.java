package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.controller.AdminLoginController;
import com.axis.entity.ContentManagementDetailsEntity;
import com.axis.enumeration.Status;

@Repository
public class ContentManagementDao extends
		GenericDao<ContentManagementDetailsEntity, Integer> {

	Logger logger = Logger.getLogger(UserDao.class);

	public ContentManagementDao() {
		super(ContentManagementDetailsEntity.class);
	}

	public List<ContentManagementDetailsEntity> getAllContentManagement() {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllContentManagement()-Start ");
		}

		String hql = "from ContentManagementDetailsEntity e where e.status=:status";

		List<ContentManagementDetailsEntity> cmsList = null;

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			
			query.setParameter("status", Status.ACTIVE);
			cmsList = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllUserWithoutAdmin(String userType)-End ");
		}

		return cmsList;

	}

	public ContentManagementDetailsEntity getCMSFromPath(String path) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCMSFromPath()-Start ");
		}


		ContentManagementDetailsEntity contentManagement = null;

		String hql = "from ContentManagementDetailsEntity c where c.path =:path ";

		try {

			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("path", path);
			contentManagement = (ContentManagementDetailsEntity) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return contentManagement;

	}

}
