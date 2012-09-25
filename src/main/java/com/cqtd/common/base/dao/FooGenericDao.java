package com.cqtd.common.base.dao;

import java.io.Serializable;
import java.util.List;

import com.cqtd.common.base.pojo.FooGenericSearch;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

/**
 * <p>
 * We have this base class for our GenericDAOs so that we don't have to override
 * and autowire the sessionFactory property for each one. That is the only
 * reason for having this class.
 * 
 * <p>
 * The  
 * 
 * @Autowired annotation tells Spring to inject the sessionFactory bean into
 *            this setter method.
 * 
 * @author Steve
 */
@SuppressWarnings("rawtypes")
public interface FooGenericDao<T, ID extends Serializable> extends
		GenericDAO<T, ID> {
	/**
	 * Remove all of the specified entities from the datastore.
	 */
	public void remove(List<T> entities);

	public boolean[] save(List<T> entities);

	public <RT> SearchResult<RT> searchAndCount(
			FooGenericSearch fooGenericSearch);
	
	//------------------------- HQL ------------------------------------------
	/**
	 * 使用HQL语句增、删、改实体对象
	 */
	public int bulkUpdateByHql(String hql);
	
	/**
	 * 使用带参数的HQL语句增、删、改实体对象
	 */
	public int bulkUpdateByHql(String hql, Object[] values);
	
	/**
     * 使用HQL语句检索数据
     */
	public List findByHql(String hql);

	/**
     * 使用带参数的HQL语句检索数据
     */
    public List findByHql(String hql, Object[] values);
    
    /**
     * 使用带参数的HQL语句检索数据
     */
    public List findByHql(String hql, List values);
    
    /**
     * 使用带可变参数的HQL语句检索数据
     */
    public List findByHqlVarParams(String hql, Object... values);

    /**
     * 使用带命名参数的HQL语句检索数据
     */
    public List findByNamedParam(String hql, String[] paramNames,Object[] values);
}