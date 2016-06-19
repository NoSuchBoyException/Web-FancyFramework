package org.fancy.framework.helpers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.fancy.framework.constants.FieldConsts.LogFields;
import org.fancy.framework.daos.impl.LogDao.LogAttr;
import org.fancy.framework.entities.LogBean;
import org.fancy.framework.exceptions.CheckedException;
import org.fancy.framework.exceptions.ValueNotFoundException;

/**
 * Used to adapt log parameters to a log bean
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class LogAdapter {

	private static final int SESSION_ID_LENGTH = 12;
	private static final String SESSION_ID_SOURCE_CHARS =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static volatile LogAdapter instance;
	
	private LogAdapter() {}
	
	public static LogAdapter getInstance() {
		LogAdapter ins = instance;
		if (null == ins) {
			synchronized (LogAdapter.class) {
				if (null == ins) {
					ins = new LogAdapter();
					instance = ins;
				}
			}
		}
		return ins;
	}

	/**
	 * Adapt log info from request to a log bean
	 * 
	 * @param request
	 *            HttpServletRequest from request
	 * @param logMsg
	 * 			  the request msg to log
	 * @return adapted log entity
	 */
	public LogBean adaptRequest(HttpServletRequest request, Object entity)
			throws ValueNotFoundException {

		LogBean logBean = buildBasicBean(request);
		logBean.setAttr(LogAttr.REQUEST);
		logBean.setLogMsg(entity.toString());
		
		// If first time log for a request, build a sessionId(a fix length
		// random string) and save into attribute 
		String sessionId;
		if (null == request.getAttribute(LogFields.SESSION_ID)) {
			sessionId = RandomStringUtils.random(SESSION_ID_LENGTH,
					SESSION_ID_SOURCE_CHARS);
			request.setAttribute(LogFields.SESSION_ID, sessionId);
		} else {
			sessionId = (String) request.getAttribute(LogFields.SESSION_ID);
		}

		logBean.setSessionId(sessionId);
		return logBean;
	}

	/**
	 * Adapt log info from request and response to a log bean
	 * 
	 * @param request
	 *            HttpServletRequest from request
	 * @param result
	 * 			  the response to log
	 * @return adapted log bean
	 */
	public LogBean adaptResponse(HttpServletRequest request, Object result) {
		LogBean logBean = buildBasicBean(request);
		logBean.setAttr(LogAttr.RESPONSE);
		logBean.setLogMsg(result.toString());
		logBean.setSessionId((String) request
				.getAttribute(LogFields.SESSION_ID));
		return logBean;
	}

	/**
	 * Adapt log info from exception to a log bean
	 * 
	 * @param request
	 *            HttpServletRequest from request
	 * @param ex
	 * 			  the exception to log
	 * @return adapted log bean
	 */
	public LogBean adaptException(HttpServletRequest request, Exception ex) {
		LogBean logBean = buildBasicBean(request);
		logBean.setAttr(LogAttr.EXCEPTION);

		// Set session id.
		if (null == request.getAttribute(LogFields.SESSION_ID)) {
			logBean.setSessionId(RandomStringUtils.random(SESSION_ID_LENGTH,
					SESSION_ID_SOURCE_CHARS));
		} else {
			logBean.setSessionId((String) request
					.getAttribute(LogFields.SESSION_ID));
		}

		// Set log msg into entity by exception type.
		if (ex instanceof CheckedException) {
			logBean.setLogMsg(ex.toString());
		} else {
			String logMsg = new StringBuilder()
					.append("{\"exception\":\"")
					.append(ex.getMessage())
					.append("\"}").toString();
			logBean.setLogMsg(logMsg);
		}

		return logBean;
	}

	private LogBean buildBasicBean(HttpServletRequest request) {
		LogBean logBean = new LogBean();
		logBean.setUserAgent(request.getHeader(LogFields.USER_AGENT));
		logBean.setUri(request.getRequestURI().substring(
				request.getRequestURI().lastIndexOf("/") + 1));
		logBean.setClientIp(request.getRemoteAddr());
		
		return logBean;
	}
	
}
