package com.axis.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao<E, PK extends Serializable> {

	private Class<E> entityClass;

	@Autowired
	protected SessionFactory sessionFactory;

	public GenericDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(E entity) {

		sessionFactory.getCurrentSession().persist(entity);
	}

	public void update(E entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	public void delete(PK id) {

		sessionFactory.getCurrentSession().delete(sessionFactory.openSession().merge(id));
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(entityClass);
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public E find(PK id) {
		
		return (E) sessionFactory.getCurrentSession().get(entityClass, id);
	}

}