package com.foo.common.base.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This class simulate the process of get helloKitty film sources
 */
public class FooUtilsHttpHelper2 {
	public static void main(String[] args) throws Exception {

		String filmName = "Cust and caution";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.torrentkitty.com/search/"
				+ filmName + "/");

		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Charset", "UTF-8,*;q=0.5");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Accept-Language", "Accept-Language");
		httpGet.setHeader("Connection", "keep-alive");
		// httpGet.setHeader("Cookie", cookie.getName() + "=" +
		// cookie.getValue());
		httpGet.setHeader("Host", "www.torrentkitty.com");
		httpGet.setHeader("Referer", "http://www.torrentkitty.com/search/"
				+ filmName + "/");
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1293.0 Safari/537.14");

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = httpclient.execute(httpGet, responseHandler);
		System.out.println(responseBody);
		httpGet.releaseConnection();

	}
}
