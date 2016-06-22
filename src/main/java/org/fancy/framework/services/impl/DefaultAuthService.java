package org.fancy.framework.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.entities.AbstractEntity;
import org.fancy.framework.services.AbstractAuthService;

public class DefaultAuthService extends AbstractAuthService {

	@Override
	public Object auth(HttpServletRequest request, AbstractEntity entity)
			throws Exception {
		
		// The mocking result of auth.
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseFields.ERROR_CODE, ErrorConsts.EC_SUCCESS);
		return responseMap;
	}
	
}
