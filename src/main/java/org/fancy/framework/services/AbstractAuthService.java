package org.fancy.framework.services;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.entities.AbstractEntity;

public abstract class AbstractAuthService {

	public abstract Object auth(HttpServletRequest request,
			AbstractEntity entity) throws Exception;

}
