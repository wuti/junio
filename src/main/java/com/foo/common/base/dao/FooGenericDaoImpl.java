package com.foo.common.base.dao;

import java.io.Serializable;
import java.util.List;

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
	public boolean[] save(List<T> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <RT> FooGenericSearchResult<RT> searchAndCount(
			FooGenericSearch fooGenericSearch) {

		Query query = getSession().createQuery(fooGenericSearch.getQueryHql());
		query.setFirstResult(fooGenericSearch.getFirstResult());
		query.setMaxResults(fooGenericSearch.getMaxResults());
		FooGenericSearchResult<RT> myResult = new FooGenericSearchResult<RT>();
		myResult.setResult(query.list());

		query = getSession().createQuery(fooGenericSearch.getCountHql());
		myResult.setTotalCount(((Number) query.uniqueResult()).intValue());
		return myResult;
	}

	@Override
	public boolean save(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] save(T... entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
