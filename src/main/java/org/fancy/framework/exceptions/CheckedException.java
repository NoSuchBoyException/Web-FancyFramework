package org.fancy.framework.exceptions;

import java.io.Serializable;

import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.json.JSONObject;

/**
 * Used to define all checked exception thrown in this project
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class CheckedException extends Exception implements Serializable {
	
	private static final long serialVersionUID = -5261336784781385580L;

	private int errorCode;
	private String errorMsg;
	
	public CheckedException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMsg = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put(ResponseFields.ERROR_CODE, errorCode);
		json.put(ResponseFields.ERROR_MSG, errorMsg);
		return json.toString();
	}
	
}
