package org.foo.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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
	/**
	 * copy目录为如下的java类的class到桌面
	 */
	@Test
	public void quickCopyTest() throws IOException {

		ClassPathResource myPath = new ClassPathResource("test.properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		// 由配置而来
		String classBaseDir = FilenameUtils.separatorsToSystem(p
				.getProperty("classBaseDir"));

		String javaBaseDir = FilenameUtils.separatorsToSystem(p
				.getProperty("javaBaseDir"));

		String javaFilePath = "D:\\zzNode\\itmsPlus\\UI_DEV\\src\\java\\com\\jscud\\www\\wwvalidator\\ABCLetterValidator.java";

		String realClassPath = classBaseDir
				+ javaFilePath.replace(javaBaseDir, "").replaceAll(".java",
						".class");

		org.junit.Assert
				.assertEquals(
						"failure - strings not same",
						realClassPath,
						"D:\\zzNode\\itmsPlus\\UI_DEV\\webapp\\WEB-INF\\classes\\com\\jscud\\www\\wwvalidator\\ABCLetterValidator.class");

		File myOriginalFile = new File(realClassPath);

		String targetFilePath = "C:\\Users\\Steve\\Desktop\\";

		Files.copy(myOriginalFile,
				new File(targetFilePath + myOriginalFile.getName()));

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

		String mypath = myList.get(0).toString();

		mySession.getTransaction().commit();

		System.out.println(FilenameUtils.getFullPath(mypath));
	}

}
