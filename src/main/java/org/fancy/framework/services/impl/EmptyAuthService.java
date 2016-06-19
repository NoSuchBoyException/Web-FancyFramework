package org.fancy.framework.services.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.services.AbstractService;

public class EmptyAuthService extends AbstractService {

	@Override
	public Object execute(HttpServletRequest request, Object[] params)
			throws Exception {
		
		// Empty auth strategy, do nothing.
		return new HashMap<>(0);
	}

}
