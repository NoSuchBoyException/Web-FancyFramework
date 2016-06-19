package org.fancy.framework.exceptions;

import java.io.Serializable;

/**
 * Used to define exception thrown when expected value not found
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class ValueNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = 5167288438465123551L;

	private String message;

	public ValueNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
