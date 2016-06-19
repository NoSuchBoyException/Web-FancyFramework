package org.fancy.framework.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.HeaderFields;
import org.fancy.framework.exceptions.CheckedException;
import org.fancy.framework.utils.PropertiesUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Used to check the validity of HTTP headers from client request
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class HeaderCheckInterceptor extends HandlerInterceptorAdapter {

	private static PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (!checkServerName(request)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		} else if (!checkHeaderFields(request)) {
			throw new CheckedException(ErrorConsts.EC_ILLEGAL_HEADERS,
					ErrorConsts.MSG_ILLEGAL_HEADERS);
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	private boolean checkHeaderFields(HttpServletRequest request)
			throws Exception {
		
		// Check Content-Type field.
		String contentType = request.getHeader(HeaderFields.CONTENT_TYPE);
		if (StringUtils.isEmpty(contentType)) {
			return false;
		}

		// Check User-Agent field.
		String userAgent = request.getHeader(HeaderFields.USER_AGENT);
		if (StringUtils.isEmpty(userAgent)) {
			return false;
		}

		// Check Accept field.
		String accept = request.getHeader(HeaderFields.ACCEPT);
		if (StringUtils.isEmpty(accept) || !accept.contains("version")) {
			return false;
		} else {
			String[] accepts = accept.trim().split(";");
			List<String> acceptVersions = propertiesUtil.getList(
					"acceptVersions", "\r");

			for (String acp : accepts) {
				if (acp.contains("version")) {
					String version = acp.split("=")[1];
					if (acceptVersions.contains(version)) {
						request.setAttribute("version", version);
						return true;
					} else {
						return false;
					}
				}
			}

			return false;
		}
	}

	private boolean checkServerName(HttpServletRequest request)
			throws Exception {

		List<String> hosts = propertiesUtil.getList("host.self", "\r");
		if (hosts.contains(request.getServerName())) {
			return true;
		} else {
			return false;
		}
	}

}
