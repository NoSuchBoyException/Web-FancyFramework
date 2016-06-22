package org.fancy.framework.daos.impl;

import java.util.HashMap;
import java.util.Map;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.daos.AbstractLoginDao;

public class LoginDao extends AbstractLoginDao {

	@Override
	public Object login(Object[] params) throws Exception {
		// Mock login result
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(ResponseFields.ERROR_CODE, ErrorConsts.EC_SUCCESS);
		return responseMap;
	}

}
