package org.foo.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

public class QuickCopyTest {
	@Test
	/**
	 * copy目录为如下的java类的class到桌面
	 */
	public void quickCopyTest() throws IOException {

		// 由前台js转换而来
		String relativePath = "D:\\zzNode/itmsPlus/UI_DEV/webapp/WEB-INF/classes";

		// 由前台js转换而来
		String classPath = "C:\\Users/Steve/Desktop/1.txt";

		// ClassPathResource myPath = new ClassPathResource("test.properties");
		// Properties p = new Properties();
		// p.load(myPath.getInputStream());
		// System.out.println(p.getProperty("path"));

		File myFile = new File(classPath);
		// 通过java的类分析得到真正的类路径
		// TODOO

	}

	public void quickMySqlTest() throws IOException {
		ClassPathResource myPath = new ClassPathResource(
				"target-mysql.properties");
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

		Query myQuery = mySession.createSQLQuery(" select value from tmp ");

		List myList = myQuery.list();

		System.out.println(myList.get(0).toString());

		mySession.getTransaction().commit();
	}

}
