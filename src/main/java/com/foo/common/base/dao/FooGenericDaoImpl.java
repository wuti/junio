package com.foo.common.base.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foo.common.base.pojo.FooGenericSearch;
import com.foo.common.base.pojo.FooGenericSearchResult;

/**
 * @author Steve
 * 
 * @param <T>
 * @param <ID>
 */
@Repository
public class FooGenericDaoImpl<T, ID extends Serializable> implements
		FooGenericDao<T, ID> {

	private SessionFactory sessionFactory;

	@Autowired
	/**
	 * Automatic autowire the sessionFactory here.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Get the current Hibernate session
	 */
	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public FooGenericSearchResult searchAndCount(
			FooGenericSearch fooGenericSearch) {

		Query query = getSession().createQuery(fooGenericSearch.getQueryHql());
		query.setFirstResult(fooGenericSearch.getFirstResult());
		query.setMaxResults(fooGenericSearch.getMaxResults());
		FooGenericSearchResult myResult = new FooGenericSearchResult();
		myResult.setResult(query.list());
		query = getSession().createQuery(fooGenericSearch.getCountHql());
		myResult.setTotalCount(((Number) query.uniqueResult()).intValue());
		return myResult;

	}

	@Override
	public Object get(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable save(Object object) {
		return getSession().save(object);
	}

	@Override
	public void delete(Object object) {
		getSession().delete(object);
	}

	@Override
	public void delete(String entityName, Object object) {
		getSession().delete(entityName, object);
	}

	@Override
	public Serializable save(String entityName, Object object) {
		return getSession().save(entityName, object);
	}

	@Override
	public void saveOrUpdate(Object object) {
		getSession().save(object);
	}

	@Override
	public void saveOrUpdate(String entityName, Object object) {
		getSession().saveOrUpdate(entityName, object);
	}

	@Override
	public void update(Object object) {
		getSession().update(object);
	}

	@Override
	public void update(String entityName, Object object) {
		getSession().update(entityName, object);
	}

	@Override
	public void flush() throws HibernateException {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}
}
