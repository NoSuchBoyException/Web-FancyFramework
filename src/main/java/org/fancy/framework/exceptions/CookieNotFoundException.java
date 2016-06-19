package org.fancy.framework.exceptions;

import java.io.Serializable;

/**
 * Used to define exception thrown when expected cookie not found
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class CookieNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = -3002759039354346830L;

	private String message;

	public CookieNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
