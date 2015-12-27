package com.axis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.RoleLevelEntity;
import com.axis.entity.SubRoleLevelEntity;
import com.axis.enumeration.Status;
import com.axis.model.SubRoleLevelModel;

@Repository
public class SubRoleLevelDao extends GenericDao<SubRoleLevelEntity, Integer> {

	private static final Logger logger = Logger
			.getLogger(SubRoleLevelDao.class);

	public SubRoleLevelDao() {
		super(SubRoleLevelEntity.class);
		// TODO Auto-generated constructor stub
	}

	public List<SubRoleLevelEntity> getSubRoleLevelListById(Integer id) {
		List<SubRoleLevelEntity> subRoleLevelEntities = null;

		String hql = "from SubRoleLevelEntity srle where srle.roleLevelEntity.rowLevelId="+ id+" and srle.status= :status";
		System.out.println("*********** mahan query HQL form :: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("status", Status.ACTIVE);
			subRoleLevelEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subRoleLevelEntities;
	}

	public List<SubRoleLevelEntity> subRoleLevelList() {
		List<SubRoleLevelEntity> subRoleLevelEntities = null;

		String hql = "from SubRoleLevelEntity srle where srle.subRoleLevelId != srle.subRoleLevelEntity.subRoleLevelId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			subRoleLevelEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subRoleLevelEntities;
	}

	public List<SubRoleLevelEntity> getSubRoleLevelModelForEdit(
			SubRoleLevelModel subRoleLevelModel) {
		List<SubRoleLevelEntity> subRoleLevelEntities = null;

		String hql = "from SubRoleLevelEntity srle where srle.subRoleLevelId="
				+ subRoleLevelModel.getSubRoleLevelId();
		System.out.println("*********** mahan query HQL form :: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			subRoleLevelEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subRoleLevelEntities;
	}

	public SubRoleLevelEntity getSubRoleLevelEntityByUniqueId(String uniqueId) {

		SubRoleLevelEntity subRoleLevelEntity = null;

		try {
			String hql = "from SubRoleLevelEntity srle where srle.uniqueId=:uniqueId";
			System.out.println("*********** mahan query HQL form :: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("uniqueId", uniqueId);
			subRoleLevelEntity = (SubRoleLevelEntity) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subRoleLevelEntity;
	}

	public List<SubRoleLevelEntity> findOthers(SubRoleLevelModel subRoleLevelModel) {
		List<SubRoleLevelEntity> subRoleLevelEntities = null;

		String hql = "from SubRoleLevelEntity srle where srle.subRoleLevelId != :subRoleLevelId";
		System.out.println("*********** mahan query HQL form :: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		try {
			query.setParameter("subRoleLevelId", subRoleLevelModel.getSubRoleLevelId());
			subRoleLevelEntities = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subRoleLevelEntities;
	}
}