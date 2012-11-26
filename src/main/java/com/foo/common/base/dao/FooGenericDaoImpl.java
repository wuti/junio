package com.foo.common.base.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * @author Steve
 * 
 * @param <T>
 * @param <ID>
 */
@Repository
public class FooGenericDaoImpl<T, ID extends Serializable> extends
		GenericDAOImpl<T, ID> implements FooGenericDao<T, ID> {

	@Autowired
	@Override
	/**
	 * Automatic autowire the sessionFactory here.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
