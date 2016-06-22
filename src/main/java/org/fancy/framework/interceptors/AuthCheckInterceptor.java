package org.fancy.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.entities.AbstractEntity;
import org.fancy.framework.exceptions.CheckedException;
import org.fancy.framework.helpers.AuthHelper;
import org.fancy.framework.services.AbstractAuthService;
import org.fancy.framework.services.impl.EmptyAuthService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Used to auth client request and make sure only legal request can access to
 * service
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

	private static AuthHelper authHelper = AuthHelper.getInstance();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// Get auth strategy by request context.
		AbstractAuthService authStrategy = authHelper.getAuthStrategy(request);
		
		if (authStrategy instanceof EmptyAuthService) {
			return super.preHandle(request, response, handler);
		} else {
			// Get token entity from request.
			AbstractEntity tokenEntity = authHelper.getTokenEntity(request);
			// Execute auth strategy.
			boolean authSuccess = (Boolean) authStrategy.auth(request,
					tokenEntity);
	
			if (authSuccess) {
				return super.preHandle(request, response, handler);
			} else {
				throw new CheckedException(ErrorConsts.EC_BAD_REQUEST,
						ErrorConsts.MSG_BAD_REQUEST);
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

}
