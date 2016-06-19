package org.fancy.framework.services;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractService {

	public abstract Object execute(HttpServletRequest request, Object[] params)
			throws Exception;
	
}
