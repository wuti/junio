package com.foo.common.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.foo.common.base.pojo.FooGenericSearch;
import com.foo.common.base.pojo.FooGenericSearchResult;

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
public interface FooGenericDao<T, ID extends Serializable> {

	public boolean save(T entity);

	public boolean[] save(T... entities);

	public boolean[] save(List<T> entities);

	public List<T> findAll();

	public <RT> FooGenericSearchResult<RT> searchAndCount(
			FooGenericSearch fooGenericSearch);

	public Session getSession();
}