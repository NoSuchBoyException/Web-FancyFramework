package org.fancy.framework.daos.impl;

import org.apache.log4j.Logger;
import org.fancy.framework.daos.AbstractLogDao;
import org.fancy.framework.entities.LogEntity;

public class LogDao extends AbstractLogDao {

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
	public void log(Object[] params) {
		LogEntity logEntity = (LogEntity) params[0];
		
		String level = LOG_LEVET;
		String userAgent = logEntity.getUserAgent();
		String sessionId = logEntity.getSessionId();
		String uri = logEntity.getUri();
		String clientIP = logEntity.getClientIp();
		String attr = logEntity.getAttr();
		String logMsg = logEntity.getLogMsg();
		
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
	}

}
