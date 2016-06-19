package org.fancy.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class HTTPUtil {

	private static final int READ_TIMEOUT = 20 * 1000;
	private static final int CONN_TIMEOUT = 20 * 1000;

	private static volatile HTTPUtil instance;
	private static IOUtil ioUtil = IOUtil.getInstance();
	
	private HTTPUtil() {}

	public static HTTPUtil getInstance() {
		HTTPUtil ins = instance;
		if (null == ins) {
			synchronized (HTTPUtil.class) {
				if (null == ins) {
					ins = new HTTPUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * do POST request of http or https
	 * 
	 * @param url
	 *            the request url, can both start with http and https
	 * @param method
	 *            the request method, can get from static class Method
	 * @param headers
	 *            the request headers
	 * @param queryString
	 *            the queryString that will put into url, do nothing while it is
	 *            null or empty
	 * @param entity
	 *            the entity that will put into request body, do nothing while
	 *            it is null or empty
	 * @param trustedHostName
	 *            the trusted host name of https connection, just check
	 *            validation of CA while it is null or empty
	 * @return return response entity string while status code is 200, otherwise
	 *         return status line string like '400 BAD REQUEST' while http
	 *         status code is not 200
	 * @throws IOException
	 */
	public String doPost(String url, List<NameValuePair> headers,
			List<NameValuePair> queryStrings, String entity,
			String trustedHostName) throws IOException {

		return doRequest("POST", url, headers, queryStrings, entity,
				trustedHostName);
	}
	
	/**
	 * do GET request of http or https
	 * 
	 * @param url
	 *            the request url, can both start with http and https
	 * @param method
	 *            the request method, can get from static class Method
	 * @param headers
	 *            the request headers
	 * @param queryString
	 *            the queryString that will put into url, do nothing while it is
	 *            null or empty
	 * @return return response entity string while status code is 200, otherwise
	 *         return status line string like '400 BAD REQUEST' while http
	 *         status code is not 200
	 * @throws IOException
	 */
	public String doGet(String url, List<NameValuePair> headers,
			List<NameValuePair> queryStrings, String trustedHostName)
			throws IOException {

		return doRequest("GET", url, headers, queryStrings, null,
				trustedHostName);
	}

	private String doRequest(String method, String url,
			List<NameValuePair> headers, List<NameValuePair> queryStrings,
			String entity, String trustedHostName) throws IOException {

		if (null == url || "".equals(url)) {
			throw new MalformedURLException();
		}

		String urls = joinQueryString(url, queryStrings);
		HttpURLConnection conn = createHttpURLConnection(urls, trustedHostName);
		
		setRequestPorperties(conn, method);
		setRequestHeaders(conn, headers);
		
		return sendRequest(conn, method, entity);
	}

	private HttpURLConnection createHttpURLConnection(String url,
			String trustedHostName) throws IOException {
		
		URL u = new URL(url);
		HttpURLConnection conn;
		
		if (url.startsWith("https")) {
			conn = (HttpsURLConnection) u.openConnection();
			setTurstHostName((HttpsURLConnection) conn, trustedHostName);
		} else {
			conn = (HttpURLConnection) u.openConnection();
		}
		
		return conn;
	}

	private void setRequestPorperties(HttpURLConnection conn, String method)
			throws ProtocolException {
		
		conn.setRequestMethod(method);
		conn.setDoOutput("POST".equals(method) ? true : false);
		conn.setConnectTimeout(CONN_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setUseCaches(false);
	}

	private void setRequestHeaders(HttpURLConnection conn,
			List<NameValuePair> headers) {
		
		if (null != headers && 0 != headers.size()) {
			for (NameValuePair pair : headers) {
				conn.setRequestProperty(pair.getName(), pair.getValue());
			}
		}
	}

	private String sendRequest(HttpURLConnection conn, String method,
			String entity) throws IOException {

		String response;
		
		if ("POST".equals(method)) {
			OutputStream output = null;
			try {
				output = conn.getOutputStream();
				output.write(entity.getBytes("UTF-8"));
				output.flush();
				
				response = getResponse(conn);
				conn.disconnect();
			} catch (IOException e) {
				throw e;
			} finally {
				try {
					if (null != output) {
						output.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return response;
		} else {
			response = getResponse(conn);
		}
		
		return response;
	}

	private String getResponse(HttpURLConnection conn) throws IOException {
		int statusCode = conn.getResponseCode();
		
		if (HttpURLConnection.HTTP_OK == statusCode) {
			return ioUtil.readStream(conn.getInputStream());
		} else {
			return new StringBuilder()
					.append(statusCode).append(" ")
					.append(conn.getResponseMessage()).toString();
		}
	}
	
	private void setTurstHostName(HttpsURLConnection conn,
			String trustedHostName) {

		if (null != trustedHostName && !"".equals(trustedHostName)) {
			final String trustHostName = trustedHostName;
			
			conn.setHostnameVerifier(new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					HostnameVerifier hv = HttpsURLConnection
							.getDefaultHostnameVerifier();
					return hv.verify(trustHostName, session);
				}
			});
		}
	}

	private String joinQueryString(String url,
			List<NameValuePair> queryStrings) {
		
		if (null != queryStrings && 0 != queryStrings.size()) {
			return new StringBuilder(url)
					.append("?")
					.append(URLEncodedUtils.format(queryStrings, "UTF-8"))
					.toString();
		} else {
			return url;
		}
	}

}
