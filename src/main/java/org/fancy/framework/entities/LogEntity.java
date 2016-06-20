package org.fancy.framework.entities;

/**
 * The log info bean
 * 
 * @author Dongfan Yang
 * @time 2015年12月31日
 */
public class LogEntity {
	
	private String sessionId;
	private String userAgent;
	private String uri;
	private String clientIp;
	private String attr;
	private String logMsg;

	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUserAgent() {
		return userAgent;
	}
	
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getClientIp() {
		return clientIp;
	}
	
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public String getAttr() {
		return attr;
	}
	
	public void setAttr(String attr) {
		this.attr = attr;
	}
	
	public String getLogMsg() {
		return logMsg;
	}
	
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}
		
}
