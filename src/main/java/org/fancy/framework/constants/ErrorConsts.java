package org.fancy.framework.constants;

/**
 * Defines all error code and error message constants
 * 
 * @author Dongfan Yang
 * @time 2015年12月31日
 */
public class ErrorConsts {

	public static final int EC_SUCCESS = 0;
	public static final int EC_FAILED = -1;

	public static final int EC_MISSING_TOKEN = -10000;
	public static final String MSG_MISSING_TOKEN = "Missing auth token";

	public static final int EC_ILLEGAL_HEADERS = -10001;
	public static final String MSG_ILLEGAL_HEADERS = "Missing or illegal headers";

	public static final int EC_ILLEGAL_PARAMS = -10002;
	public static final String MSG_ILLEGAL_PARAMS = "Missing or illegal parameters";

	public static final int EC_AUTH_FAILED = -10403;
	public static final String MSG_AUTH_FAILED = "Auth failed";

	public static final int EC_SERVER_INTERNAL_ERROR = -20503;
	public static final String MSG_SERVER_INTERNAL_ERROR = "Server internal error";
	
}
