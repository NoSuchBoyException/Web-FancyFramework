package org.fancy.framework;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.fancy.framework.utils.HTTPUtil;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHeaderCheck {

	private static final String URL = "http://localhost:8080/fancy/login";
	private static final String ACCEPT = "application/json; version=1.0";
	private static final String CONTENT_TYPE = "application/json";
	private static final String USER_AGENT = "Chrome/45.0.2454.101";

	private static HTTPUtil hTTPUtil = HTTPUtil.getInstance();

	private static JSONObject request;
	private static JSONObject response;

	@BeforeClass
	public static void init() {
		request = new JSONObject();
		response = new JSONObject();
	}

	@Before
	public void beforeTest() {
		request = new JSONObject();
	}

	@After
	public void afterTest() {
		System.out.println(request);
		System.out.println(response);
	}

	@Test()
	public void requestWithoutHeaders() {
		try {
			request.put("username", "dfyang");
			request.put("password", "123456");

			response = new JSONObject(hTTPUtil.doPost(URL, null, null,
					request.toString(), null));

			assertEquals(response.getInt("errorCode"), -20400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	public void requestWithoutAccept() {
		try {
			List<NameValuePair> headers = new ArrayList<>();
			headers.add(new BasicNameValuePair("Content-Type", CONTENT_TYPE));
			headers.add(new BasicNameValuePair("User-Agent", USER_AGENT));

			request.put("username", "dfyang");
			request.put("password", "123456");

			response = new JSONObject(hTTPUtil.doPost(URL, null, null,
					request.toString(), null));

			assertEquals(response.getInt("errorCode"), -20400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	public void requestWithoutContentType() {
		try {
			List<NameValuePair> headers = new ArrayList<>();
			headers.add(new BasicNameValuePair("Accept", ACCEPT));
			headers.add(new BasicNameValuePair("User-Agent", USER_AGENT));

			request.put("username", "dfyang");
			request.put("password", "123456");

			response = new JSONObject(hTTPUtil.doPost(URL, null, null,
					request.toString(), null));

			assertEquals(response.getInt("errorCode"), -20400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	public void requestWithoutUserAgent() {
		try {
			List<NameValuePair> headers = new ArrayList<>();
			headers.add(new BasicNameValuePair("Accept", ACCEPT));
			headers.add(new BasicNameValuePair("Content-Type", CONTENT_TYPE));

			request.put("username", "dfyang");
			request.put("password", "123456");

			response = new JSONObject(hTTPUtil.doPost(URL, null, null,
					request.toString(), null));

			assertEquals(response.getInt("errorCode"), -20400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	public void requestWithWrongAccept() {
		try {
			List<NameValuePair> headers = new ArrayList<>();
			headers.add(new BasicNameValuePair("Content-Type", CONTENT_TYPE));
			headers.add(new BasicNameValuePair("User-Agent", USER_AGENT));
			headers.add(new BasicNameValuePair("Accept",
					"application/json; version=1111.0"));

			request.put("username", "dfyang");
			request.put("password", "123456");

			response = new JSONObject(hTTPUtil.doPost(URL, null, null,
					request.toString(), null));

			assertEquals(response.getInt("errorCode"), -20400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
