package org.fancy.framework.helpers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.fancy.framework.exceptions.CookieNotFoundException;
import org.fancy.framework.exceptions.ValueNotFoundException;
import org.fancy.framework.utils.CookieUtil;

public class ExtractHelper {

	private static volatile ExtractHelper instance;
	private static CookieUtil cookieUtil = CookieUtil.getInstance();
	
	private ExtractHelper() {}
	
	public static ExtractHelper getInstance() {
		ExtractHelper ins = instance;
		if (null == ins) {
			synchronized (ExtractHelper.class) {
				if (null == ins) {
					ins = new ExtractHelper();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * get value from request header, parameter or cookie by input field
	 * 
	 * @param request
	 *            HttpServletRequest from client to get value
	 * @param field
	 *            the key to find the expected value
	 * @return the expected string value
	 * @throws ValueNotFoundException
	 *             thrown when value not found or empty
	 */
	public String getValueFromRequest(HttpServletRequest request,
			String field) throws ValueNotFoundException {

		// Extract value from HTTP header.
		String value = request.getHeader(field);
		if (!StringUtils.isEmpty(value)) {
			return value;
		}

		// Extract value from query strings if it not found in HTTP header.
		value = (String) request.getParameter(field);
		if (!StringUtils.isEmpty(value)) {
			return value;
		}

		// Extract value from cookie if it both not found in HTTP header and
		// query strings.
		try {
			value = cookieUtil.getCookie(request, field).getValue();
			if (!StringUtils.isEmpty(value)) {
				return value;
			}
		} catch (CookieNotFoundException e) {
			throw new ValueNotFoundException("Value not found");
		}

		throw new ValueNotFoundException("Value not found");
	}
	
}
