package org.fancy.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor used to forward client request to back-end controller
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
public class ForwardInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String version = (String) request.getAttribute("version");
		String forwardPath = new StringBuilder()
				.append(request.getServletPath()).append("_v").append(version)
				.toString();
		request.getRequestDispatcher(forwardPath).forward(request, response);

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

}
