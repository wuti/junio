package com.cqtd.common.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cqtd.common.base.pojo.FooGenericSearch;
import com.google.common.base.Preconditions;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.SearchResult;

@SuppressWarnings("rawtypes")
@Repository
public class FooGenericDaoImpl<T, ID extends Serializable> extends
		GenericDAOImpl<T, ID> implements FooGenericDao<T, ID>{

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

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
	
	//------------------------- HQL ------------------------------------------
	/**
	 * 使用HQL语句增、删、改实体对象
	 */
	public int bulkUpdateByHql(String hql){
		return getHibernateTemplate().bulkUpdate(hql);
	}
	
	/**
	 * 使用带参数的HQL语句增、删、改实体对象
	 */
	public int bulkUpdateByHql(String hql, Object[] values){
		return getHibernateTemplate().bulkUpdate(hql, values);
	}
	
	/**
     * 使用HQL语句检索数据
     */
	public List findByHql(String hql){
		return getHibernateTemplate().find(hql);
	}

	/**
     * 使用带参数的HQL语句检索数据
     */
    public List findByHql(String hql, Object[] values){
    	return getHibernateTemplate().find(hql, values);
    }
    
    /**
     * 使用带参数的HQL语句检索数据
     */
    public List findByHql(String hql, List values){
    	return findByHql(hql, values.toArray());
    }
    
    /**
     * 使用带可变参数的HQL语句检索数据
     */
    public List findByHqlVarParams(String hql, Object... values){
    	return findByHql(hql, values);
    }

    /**
     * 使用带命名参数的HQL语句检索数据
     */
    public List findByNamedParam(String hql, String[] paramNames,Object[] values){
    	return getHibernateTemplate().findByNamedParam(hql, paramNames,values);
    }
    
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
