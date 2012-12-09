package com.foo.common.base.dao;

import java.io.Serializable;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

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

}