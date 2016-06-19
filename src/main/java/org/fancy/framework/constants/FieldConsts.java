package org.fancy.framework.constants;

/**
 * Defines all field constants such as header fields
 * 
 * @author Dongfan Yang
 * @time 2015年12月31日
 */
public class FieldConsts {

	public static class HeaderFields {
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String USER_AGENT = "User-Agent";
		public static final String ACCEPT = "Accept";
	}
	
	public static class ResponseFields {
		public static final String ERROR_CODE = "errorCode";
		public static final String ERROR_MSG = "errorMsg";
		public static final String RESULT = "result";
	}
	
	public static class LogFields {
		public static final String SESSION_ID = "sessionId";
		public static final String USER_AGENT = "User-Agent";
		public static final String LEVEL = "level";
		public static final String URI = "URI";
		public static final String MESSAGE = "message";
		public static final String CLIENT_IP = "clientIP";
		public static final String ATTR = "attr";
	}
	
	public static class AuthFields {
		public static final String TOKEN = "token";
	}
	
}
