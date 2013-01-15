package com.foo.common.base.utils;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Copies;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class FooUtilsHttpHelper {
	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://192.168.2.112:9980/itms");

		HttpResponse response1 = httpclient.execute(httpGet);

		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the
		// network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content or abort
		// request
		// execution by calling HttpGet#releaseConnection().

		// System.out.println(response1.getStatusLine());
		HttpEntity entity1 = response1.getEntity();
		// do something useful with the response body
		// and ensure it is fully consumed
		EntityUtils.consume(entity1);

		for (Header string : response1.getAllHeaders()) {
			System.out.println(string);
		}
		System.out.println("------------------");

		Cookie cookie = null;
		for (Cookie tmpCookie : httpclient.getCookieStore().getCookies()) {

			if (tmpCookie != null
					&& Strings.nullToEmpty(tmpCookie.getName()).equals(
							"JSESSIONID")) {
				cookie = tmpCookie;
				break;
			}
		}

		System.out.println(cookie.getName() + "=" + cookie.getValue());

		httpGet.releaseConnection();

		Preconditions.checkNotNull(cookie);

		HttpPost httpPost = new HttpPost(
				"http://192.168.2.112:9980/itms/pages/security/loginAction.action;jsessionid=51775AE9C6A24BC24937F799E7900536");

		// httpPost.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		// httpPost.setHeader("Accept-Language", "Accept-Language");
		// httpPost.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Cookie", cookie.getName() + "=" +
		// cookie.getValue());
		// httpPost.setHeader("Host", "192.168.2.112:9980");
		// httpPost.setHeader("Referer",
		// "http://192.168.2.112:9980/itms/pages/security/goLoginAction.action");
		// httpPost.setHeader("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		// httpPost.setHeader("Content-Type",
		// "application/x-www-form-urlencoded");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user.password", "asb#1234"));
		nvps.add(new BasicNameValuePair("user.userName", "administrator"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// HttpResponse response2 = httpclient.execute(httpPost);
		//
		// for (Header header : response2.getAllHeaders()) {
		// System.out.println(header.toString());
		// }

		// ResponseHandler<String> responseHandler = new BasicResponseHandler();
		// String responseBody = httpclient.execute(httpPost, responseHandler);
		// System.out.println(responseBody);
		response1 = httpclient.execute(httpPost);
		System.out.println(response1.getStatusLine());
		httpPost.releaseConnection();

		Thread.sleep(6000);

		System.out.println("---------------------");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		HttpPost httpPost2 = new HttpPost(
				"http://192.168.2.112:9980/itms/pages/generic/refreshUserTables.action");
		httpPost2.addHeader("Cookie",
				cookie.getName() + "=" + cookie.getValue());
		// httpPost2.setHeader("Cookie",
		// cookie.getName() + "=" + cookie.getValue());

		String responseBody = httpclient.execute(httpPost2, responseHandler);
		System.out.println(responseBody);
		httpPost2.releaseConnection();

	}
}
