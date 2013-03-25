package com.foo.common.base.utils;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.core.io.ClassPathResource;

public class FooUtilsHibernate {
	private static final SessionFactory sessionFactory = null;

	public static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.buildServiceRegistry();
			return new Configuration().configure().buildSessionFactory(
					serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(new ClassPathResource("target-oracle.properties")
				.exists());
		ClassPathResource myPath = new ClassPathResource(
				"target-oracle.properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		Configuration configuration = new Configuration().setProperties(p);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();

		SessionFactory myFactory = configuration
				.buildSessionFactory(serviceRegistry);

		Session session = myFactory.openSession();

		Transaction tx = null;

		// See:http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch13.html#transactions-demarcation-nonmanaged
		try {
			tx = session.beginTransaction();
			tx.setTimeout(200);
			doInTransaction(session);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}
	}

	public static void doInTransaction(Session session) {
		
		//从excel解析数据
		
		
		
		Query myQuery = session
				.createSQLQuery(" select * from itms_device_static_1 ");

		System.out.println(myQuery.list().size());

		myQuery = session
				.createSQLQuery(" delete from ITMS_RESTART_DEVICE_CONFIG ");
		myQuery.executeUpdate();
	}

}
