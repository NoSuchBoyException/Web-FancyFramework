package org.fancy.framework.services;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.entities.AbstractEntity;

public abstract class AbstractLoginService {

	public abstract Object login(HttpServletRequest request,
			AbstractEntity entity) throws Exception;

}
