package org.fancy.framework.daos.impl;

import org.apache.log4j.Logger;
import org.fancy.framework.daos.AbstractDao;
import org.fancy.framework.entities.LogBean;

public class LogDao extends AbstractDao {

	private static final String LOG_SEPARATOR = "|";
	private static final String LOG_LEVET = LogLevel.INFO;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static class LogLevel {
		public static final String ERROR = "ERROR";
		public static final String WARN = "WARN";
		public static final String INFO = "INFO";
		public static final String DEBUG = "DEBUG";
		public static final String OFF = "OFF";
	}
	
	public static class LogAttr {
		public static final String REQUEST = "Request";
		public static final String RESPONSE = "Response";
		public static final String EXCEPTION = "Exception";
	}
	
	@Override
	public Object execute(Object[] params) {
		LogBean logBean = (LogBean) params[0];
		
		String level = LOG_LEVET;
		String userAgent = logBean.getUserAgent();
		String sessionId = logBean.getSessionId();
		String uri = logBean.getUri();
		String clientIP = logBean.getClientIp();
		String attr = logBean.getAttr();
		String logMsg = logBean.getLogMsg();
		
		String entireMsg = new StringBuilder()
				.append(sessionId).append(LOG_SEPARATOR)
				.append(userAgent).append(LOG_SEPARATOR)
				.append(uri).append(LOG_SEPARATOR)
				.append(clientIP).append(LOG_SEPARATOR)
				.append(attr).append(LOG_SEPARATOR)
				.append(logMsg).toString();
		
		switch (level) {
		case LogLevel.ERROR:
			logger.error(entireMsg);
			break;
		case LogLevel.WARN:
			logger.warn(entireMsg);
			break;
		case LogLevel.INFO:
			logger.info(entireMsg);
			break;
		case LogLevel.DEBUG:
			logger.debug(entireMsg);
			break;
		case LogLevel.OFF:
			break;
		default:
			logger.info(entireMsg);
			break;
		}
		
		return true;
	}

}
