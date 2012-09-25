package com.cqtd.common.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqtd.common.base.pojo.FooGenericSearch;
import com.google.common.base.Preconditions;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class FooGenericDaoImpl<T, ID extends Serializable> extends
		GenericDAOImpl<T, ID> implements FooGenericDao<T, ID> {

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public <RT> SearchResult<RT> searchAndCount(
			FooGenericSearch fooGenericSearch) {
		Preconditions.checkNotNull(fooGenericSearch);
		SearchResult<RT> result = new SearchResult<RT>();

		Integer total = (Integer) getSession()
				.createQuery(fooGenericSearch.getCountHql()).iterate().next();

		Query query = getSession().createQuery(fooGenericSearch.getQueryHql());
		query.setFirstResult(fooGenericSearch.getFirstResult());
		query.setMaxResults(fooGenericSearch.getMaxResults());

		result.setTotalCount(total.intValue());
		result.setResult(query.list());
		return result;
	}

	@SuppressWarnings("unchecked")
	public void remove(List<T> entities) {
		super.remove((T[]) entities.toArray());
	}

	@SuppressWarnings("unchecked")
	public boolean[] save(List<T> entities) {
		return super.save((T[]) entities.toArray());
	}
}
