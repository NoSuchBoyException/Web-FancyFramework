package org.fancy.framework.helpers;

import java.util.HashMap;
import java.util.Map;

import org.fancy.framework.constants.FieldConsts.ResponseFields;

/**
 * Used to transfer response info or error to map
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class TransHelper {

	private static volatile TransHelper instance;

	private TransHelper() {}

	public static TransHelper getInstance() {
		TransHelper ins = instance;
		if (null == ins) {
			synchronized (TransHelper.class) {
				if (null == ins) {
					ins = new TransHelper();
					instance = ins;
				}
			}
		}
		return ins;
	}

	public Map<String, Object> transResultResp(int errorCode, Object result) {
		HashMap<String, Object> response = new HashMap<>();
		response.put(ResponseFields.ERROR_CODE, errorCode);
		response.put(ResponseFields.RESULT, result);
		return response;
	}

	public Map<String, Object> transErrorResp(int errorCode, Object errorMsg) {
		HashMap<String, Object> response = new HashMap<>();
		response.put(ResponseFields.ERROR_CODE, errorCode);
		response.put(ResponseFields.ERROR_MSG, errorMsg);
		return response;
	}

}
