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
public interface FooGenericDao<T, ID extends Serializable> extends
		GenericDAO<T, ID> {
	/**
	 * Remove all of the specified entities from the datastore.
	 */
	public void remove(List<T> entities);

	public boolean[] save(List<T> entities);

	public <RT> SearchResult<RT> searchAndCount(
			FooGenericSearch fooGenericSearch);

}