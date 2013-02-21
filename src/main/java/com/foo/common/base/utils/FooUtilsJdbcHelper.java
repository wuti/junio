package com.foo.common.base.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class FooUtilsJdbcHelper {
	public static void main(String[] args) throws Exception {
		ClassPathResource myPath = new ClassPathResource(
				"target-oracle.properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection myConnection = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.2.112:1521:itms", "itmstest",
				"itmstest");
		Statement myStatement = myConnection.createStatement();
		ResultSet rs = myStatement
				.executeQuery("select count(*) from itms_device_static");
		while (rs.next()) {
			System.out.println(rs.getInt(1));
		}
		rs.close();
		myStatement.close();
		myConnection.close();
	}
}
