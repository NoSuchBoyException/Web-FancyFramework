package org.fancy.framework.services.impl;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.entities.AbstractEntity;
import org.fancy.framework.services.AbstractAuthService;

public class EmptyAuthService extends AbstractAuthService {

	@Override
	public Object auth(HttpServletRequest request, AbstractEntity entity)
			throws Exception {

		// Empty auth strategy, do nothing.
		return null;
	}

}
