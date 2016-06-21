package org.fancy.framework.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.services.AbstractService;

public class LoginService extends AbstractService {

	@Override
	public Object execute(Object[] params) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseFields.ERROR_CODE, ErrorConsts.EC_SUCCESS);
		return responseMap;
	}

}
