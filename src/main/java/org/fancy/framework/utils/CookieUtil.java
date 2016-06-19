package org.fancy.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.exceptions.CookieNotFoundException;

public class CookieUtil {

	private static volatile CookieUtil instance;
	
	private CookieUtil() {}
	
	public static CookieUtil getInstance() {
		CookieUtil ins = instance;
		if (null == ins) {
			synchronized (CookieUtil.class) {
				if (null == ins) {
					ins = new CookieUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * Add a cookie
	 * 
	 * @param name
	 *            cookie name.
	 * @param value
	 *            cookie value.
	 * @param expiry
	 *            an integer specifying the maximum age of the cookie in
	 *            seconds; if negative, means the cookie is not stored; if zero,
	 *            deletes the cookie.
	 * @param path
	 *            a path for the cookie to which the client should return the
	 *            cookie.
	 * @param httpOnly
	 *            sets if this cookie will be hidden from scripts on the client
	 *            side.
	 * @param isSecure
	 *            indicates to the browser whether the cookie should only be
	 *            sent using a secure protocol, such as HTTPS or SSL.
	 * @param domain
	 *            the domain within which this cookie should be presented. A
	 *            domain name begins with a dot (for example .foo.com means for
	 *            example, www.foo.com, but not a.b.foo.com). By default,
	 *            cookies are only returned to the server that sent them.
	 */
	public void addCookie(HttpServletResponse response, String name,
			String value, Integer expiry, String path, boolean httpOnly,
			boolean isSecure, String domain) {

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setSecure(isSecure);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}

	/**
	 * Add a cookie
	 * 
	 * @param name
	 *            cookie name.
	 * @param value
	 *            cookie value.
	 * @param expiry
	 *            an integer specifying the maximum age of the cookie in
	 *            seconds; if negative, means the cookie is not stored; if zero,
	 *            deletes the cookie.
	 * @param path
	 *            a path for the cookie to which the client should return the
	 *            cookie.
	 * @param httpOnly
	 *            sets if this cookie will be hidden from scripts on the client
	 *            side.
	 * @param isSecure
	 *            indicates to the browser whether the cookie should only be
	 *            sent using a secure protocol, such as HTTPS or SSL.
	 */
	public void addCookie(HttpServletResponse response, String name,
			String value, Integer expiry, String path, boolean httpOnly,
			boolean isSecure) {

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setSecure(isSecure);
		response.addCookie(cookie);
	}

	/**
	 * get cookie by name
	 * 
	 * @param name
	 *            cookie name.
	 * @return the cookie of a request.
	 * @throws CookieNotFoundException
	 *            throws when the cookie is empty.
	 */
	public Cookie getCookie(HttpServletRequest request, String name)
			throws CookieNotFoundException {

		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			throw new CookieNotFoundException("Cookie not found.");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
			throw new CookieNotFoundException("Cookie not found.");
		}
	}

	/**
	 * delete cookie by name
	 * 
	 * @param name
	 *            cookiename
	 */
	public void deleteCookie(HttpServletResponse response,
			HttpServletRequest request, String name) {

		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			return;
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}

}
