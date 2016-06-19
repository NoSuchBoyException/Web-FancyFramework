package org.fancy.framework.helpers;

import java.util.HashMap;
import java.util.Map;

import org.fancy.framework.constants.FieldConsts.ResponseFields;

/**
 * Used to build response map
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class JSONHelper {

	private static volatile JSONHelper instance;

	private JSONHelper() {
	}

	public static JSONHelper getInstance() {
		JSONHelper ins = instance;
		if (null == ins) {
			synchronized (JSONHelper.class) {
				if (null == ins) {
					ins = new JSONHelper();
					instance = ins;
				}
			}
		}
		return ins;
	}

	public Map<String, Object> buildResultResp(int errorCode, Object result) {
		HashMap<String, Object> response = new HashMap<>();
		response.put(ResponseFields.ERROR_CODE, errorCode);
		response.put(ResponseFields.RESULT, result);
		return response;
	}

	public Map<String, Object> buildErrorResp(int errorCode, Object errorMsg) {
		HashMap<String, Object> response = new HashMap<>();
		response.put(ResponseFields.ERROR_CODE, errorCode);
		response.put(ResponseFields.ERROR_MSG, errorMsg);
		return response;
	}

}
