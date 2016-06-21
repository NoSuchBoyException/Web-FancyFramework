package org.fancy.framework.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.exceptions.CheckedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Used to handle and dispatch all exceptions thrown in project
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final String RUNNTIME_EXCEPTION = "runtimeException";
	private static final String CHECKED_EXCEPTION = "checkedException";

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		// Caught checked exception.
		if (ex instanceof CheckedException) {
			return new ModelAndView("forward:/" + CHECKED_EXCEPTION);
			
		// Caught runtime exception.
		} else {
			return new ModelAndView("forward:/" + RUNNTIME_EXCEPTION);
		}
	}
	
}
