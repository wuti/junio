package com.foo.common.base.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.core.io.ClassPathResource;

import com.foo.example.util.ItmsOrderModel;
import com.foo.example.util.ItmsOrderServiceModel;
import com.foo.example.util.ItmsOrderServiceModel.MyPK;
import com.google.common.collect.Lists;

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
				.addAnnotatedClass(ItmsOrderModel.class)
				.addAnnotatedClass(ItmsOrderServiceModel.class)
				.buildSessionFactory(serviceRegistry);

		Session session = myFactory.openSession();

		Transaction tx = null;

		// See:http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch13.html#transactions-demarcation-nonmanaged
		try {
			tx = session.beginTransaction();
			// tx.setTimeout(200);
			doInTransaction(session);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public static void doInTransaction(Session session) throws IOException {

		// TODO
		// 从excel解析数据

		// 载入区域码和认证地址的映射
		ClassPathResource myPath = new ClassPathResource(
				"target-itmsAuthURL.properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		ClassPathResource myPath2 = new ClassPathResource(
				"target-itmsAreaCode.properties");
		Properties p2 = new Properties();
		p2.load(myPath2.getInputStream());

		String areaCode = "11012";
		String mac = "B0:75:D5:E1:F3:E3";
		String pppoe_account = "SNA2642139A@ITV";

		// Insert order here
		ItmsOrderModel myObjectNew = new ItmsOrderModel();
		myObjectNew.setReceived_order_id("Z" + new Date().getTime());
		myObjectNew.setReceived_order_lhs("Z" + new Date().getTime());
		myObjectNew.setOrder_date(new Date());
		myObjectNew.setOrder_service_type("0");
		myObjectNew.setOrder_remark("0");
		myObjectNew.setOrder_customer_kind("1");
		myObjectNew.setSystem_domain(Integer.valueOf(p2.getProperty(areaCode,
				"1000000000")));// 默认填写四川电信
		myObjectNew.setCorporation_domain(0);
		myObjectNew.setAd_no(mac);
		myObjectNew.setPppoe_account(pppoe_account);
		myObjectNew.setReceived_date(new Date());
		myObjectNew.setOrder_status("3");
		myObjectNew.setOrder_deal_date(new Date());
		myObjectNew.setOrder_done_flag("1");
		myObjectNew.setDummy_flag("1");
		myObjectNew.setPppoe_password("OLDORDER");
		myObjectNew.setFlow_type("0");
		myObjectNew.setUnique_user_id("B0:75:D5:E1:F3:E3^55388");
		myObjectNew.setLifetime(-999);
		myObjectNew.setMac(mac);
		myObjectNew.setProduct_type("1");
		session.save(myObjectNew);
		System.out.println(myObjectNew.getOrder_id());

		// Insert order serviceType
		List<ItmsOrderServiceModel> myModelList = Lists.newArrayList();
		ItmsOrderServiceModel myServiceModel = new ItmsOrderServiceModel();
		MyPK myPK = myServiceModel.new MyPK();
		myPK.setOrder_id(myObjectNew.getOrder_id());
		myPK.setArgs_name("iptv_userID");
		myServiceModel.setId(myPK);
		myServiceModel.setService("IPTV");
		myServiceModel.setService_id(81);
		myServiceModel.setService_flag("A");
		myServiceModel.setArgs_value_new(myObjectNew.getPppoe_account());
		myModelList.add(myServiceModel);

		myServiceModel = new ItmsOrderServiceModel();
		myPK = myServiceModel.new MyPK();
		myPK.setOrder_id(myObjectNew.getOrder_id());
		myPK.setArgs_name("iptv_password");
		myServiceModel.setId(myPK);
		myServiceModel.setService("IPTV");
		myServiceModel.setService_id(81);
		myServiceModel.setService_flag("A");
		myServiceModel.setArgs_value_new(myObjectNew.getPppoe_password());
		myModelList.add(myServiceModel);

		myServiceModel = new ItmsOrderServiceModel();
		myPK = myServiceModel.new MyPK();
		myPK.setOrder_id(myObjectNew.getOrder_id());
		myPK.setArgs_name("AUTH_URL");
		myServiceModel.setId(myPK);
		myServiceModel.setService("IPTV");
		myServiceModel.setService_id(81);
		myServiceModel.setService_flag("A");
		myServiceModel.setArgs_value_new(p.getProperty(areaCode, ""));
		myModelList.add(myServiceModel);

		myServiceModel = new ItmsOrderServiceModel();
		myPK = myServiceModel.new MyPK();
		myPK.setOrder_id(myObjectNew.getOrder_id());
		myPK.setArgs_name("device_id");
		myServiceModel.setId(myPK);
		myServiceModel.setService("IPTV");
		myServiceModel.setService_id(81);
		myServiceModel.setService_flag("A");
		myServiceModel.setArgs_value_new(myObjectNew.getAd_no());
		myModelList.add(myServiceModel);

		myServiceModel = new ItmsOrderServiceModel();
		myPK = myServiceModel.new MyPK();
		myPK.setOrder_id(myObjectNew.getOrder_id());
		myPK.setArgs_name("OLDORDER");
		myServiceModel.setId(myPK);
		myServiceModel.setService("IPTV");
		myServiceModel.setService_id(81);
		myServiceModel.setService_flag("A");
		myServiceModel.setArgs_value_new("1");
		myModelList.add(myServiceModel);

		for (ItmsOrderServiceModel itmsOrderServiceModel : myModelList) {
			session.save(itmsOrderServiceModel);
		}
	}
}
