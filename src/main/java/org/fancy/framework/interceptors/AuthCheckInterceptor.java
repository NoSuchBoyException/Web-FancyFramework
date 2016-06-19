package org.fancy.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.exceptions.CheckedException;
import org.fancy.framework.helpers.AuthHelper;
import org.fancy.framework.services.AbstractService;
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

		AbstractService authStrategy = authHelper.getAuthStrategy(request);
		
		// Not auth.
		if (authStrategy instanceof EmptyAuthService) {
			return super.preHandle(request, response, handler);
		} else {
			// get token entity from request
			Object tokenEntity = authHelper.getTokenEntity(request, null);
			// execute auth strategy got by request context
			boolean authSuccess = (Boolean) authStrategy.execute(request,
					new Object[] {tokenEntity});

			if (authSuccess) {
				return super.preHandle(request, response, handler);
			} else {
				throw new CheckedException(ErrorConsts.EC_AUTH_FAILED,
						ErrorConsts.MSG_AUTH_FAILED);
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
