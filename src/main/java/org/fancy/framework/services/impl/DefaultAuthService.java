package org.fancy.framework.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.services.AbstractService;

public class DefaultAuthService extends AbstractService {

	@Override
	public Object execute(HttpServletRequest request, Object[] params)
			throws Exception {
		
		// Do auth job here and return auth result.
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseFields.ERROR_CODE, ErrorConsts.EC_SUCCESS);
		return responseMap;
	}
	
}
