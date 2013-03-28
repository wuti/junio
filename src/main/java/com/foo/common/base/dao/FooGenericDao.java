package com.foo.common.base.dao;

import java.io.Serializable;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	public Session getSession();

	public FooGenericSearchResult searchAndCount(
			FooGenericSearch fooGenericSearch);

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance. (If the
	 * instance is already associated with the session, return that instance.
	 * This method never returns an uninitialized instance.)
	 * 
	 * @param clazz
	 *            a persistent class
	 * @param id
	 *            an identifier
	 * 
	 * @return a persistent instance or null
	 */
	public Object get(Class<T> clazz, Serializable id);

	/**
	 * Persist the given transient instance, first assigning a generated
	 * identifier. (Or using the current value of the identifier property if the
	 * <tt>assigned</tt> generator is used.) This operation cascades to
	 * associated instances if the association is mapped with
	 * {@code cascade="save-update"}
	 * 
	 * @param object
	 *            a transient instance of a persistent class
	 * 
	 * @return the generated identifier
	 */
	public Serializable save(Object object);

	/**
	 * Persist the given transient instance, first assigning a generated
	 * identifier. (Or using the current value of the identifier property if the
	 * <tt>assigned</tt> generator is used.) This operation cascades to
	 * associated instances if the association is mapped with
	 * {@code cascade="save-update"}
	 * 
	 * @param entityName
	 *            The entity name
	 * @param object
	 *            a transient instance of a persistent class
	 * 
	 * @return the generated identifier
	 */
	public Serializable save(String entityName, Object object);

	/**
	 * Either {@link #save(Object)} or {@link #update(Object)} the given
	 * instance, depending upon resolution of the unsaved-value checks (see the
	 * manual for discussion of unsaved-value checking).
	 * <p/>
	 * This operation cascades to associated instances if the association is
	 * mapped with {@code cascade="save-update"}
	 * 
	 * @param object
	 *            a transient or detached instance containing new or updated
	 *            state
	 * 
	 * @see Session#save(java.lang.Object)
	 * @see Session#update(Object object)
	 */
	public void saveOrUpdate(Object object);

	/**
	 * Either {@link #save(String, Object)} or {@link #update(String, Object)}
	 * the given instance, depending upon resolution of the unsaved-value checks
	 * (see the manual for discussion of unsaved-value checking).
	 * <p/>
	 * This operation cascades to associated instances if the association is
	 * mapped with {@code cascade="save-update"}
	 * 
	 * @param entityName
	 *            The entity name
	 * @param object
	 *            a transient or detached instance containing new or updated
	 *            state
	 * 
	 * @see Session#save(String,Object)
	 * @see Session#update(String,Object)
	 */
	public void saveOrUpdate(String entityName, Object object);

	/**
	 * Update the persistent instance with the identifier of the given detached
	 * instance. If there is a persistent instance with the same identifier, an
	 * exception is thrown. This operation cascades to associated instances if
	 * the association is mapped with {@code cascade="save-update"}
	 * 
	 * @param object
	 *            a detached instance containing updated state
	 */
	public void update(Object object);

	/**
	 * Update the persistent instance with the identifier of the given detached
	 * instance. If there is a persistent instance with the same identifier, an
	 * exception is thrown. This operation cascades to associated instances if
	 * the association is mapped with {@code cascade="save-update"}
	 * 
	 * @param entityName
	 *            The entity name
	 * @param object
	 *            a detached instance containing updated state
	 */
	public void update(String entityName, Object object);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an
	 * instance associated with the receiving <tt>Session</tt> or a transient
	 * instance with an identifier associated with existing persistent state.
	 * This operation cascades to associated instances if the association is
	 * mapped with {@code cascade="delete"}
	 * 
	 * @param object
	 *            the instance to be removed
	 */
	public void delete(Object object);

	/**
	 * Remove a persistent instance from the datastore. The <b>object</b>
	 * argument may be an instance associated with the receiving
	 * <tt>Session</tt> or a transient instance with an identifier associated
	 * with existing persistent state. This operation cascades to associated
	 * instances if the association is mapped with {@code cascade="delete"}
	 * 
	 * @param entityName
	 *            The entity name for the instance to be removed.
	 * @param object
	 *            the instance to be removed
	 */
	public void delete(String entityName, Object object);

	/**
	 * Force this session to flush. Must be called at the end of a unit of work,
	 * before committing the transaction and closing the session (depending on
	 * {@link #setFlushMode(FlushMode)}, {@link Transaction#commit()} calls this
	 * method).
	 * <p/>
	 * <i>Flushing</i> is the process of synchronizing the underlying persistent
	 * store with persistable state held in memory.
	 * 
	 * @throws HibernateException
	 *             Indicates problems flushing the session or talking to the
	 *             database.
	 */
	public void flush() throws HibernateException;

	/**
	 * Completely clear the session. Evict all loaded instances and cancel all
	 * pending saves, updates and deletions. Do not close open iterators or
	 * instances of <tt>ScrollableResults</tt>.
	 */
	public void clear();
}