package com.foo.example.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.core.io.ClassPathResource;

public class LocalHibernateUtils {
	private static final SessionFactory sessionFactory = null;

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
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

		Session mySession = myFactory.openSession();

		mySession.beginTransaction();

		Query myQuery = mySession
				.createSQLQuery(" select * from itms_device_static ");

		System.out.println(myQuery.list().size());

		mySession.getTransaction().commit();
	}
}
