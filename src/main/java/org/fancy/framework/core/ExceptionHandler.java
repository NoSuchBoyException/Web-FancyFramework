package org.fancy.framework.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.exceptions.CheckedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handle and dispatch all exceptions thrown in project
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
		
		String forwardPath;
		String errorMsg;
		int errorCode;
		
		// Caught checked exception.
		if (ex instanceof CheckedException) {
			errorCode = ((CheckedException) ex).getErrorCode();
			errorMsg = ((CheckedException) ex).getMessage();
			forwardPath = CHECKED_EXCEPTION;
			
		// Caught runtime exception.
		} else {
			errorCode = ErrorConsts.EC_FAILED;
			errorMsg = ex.getMessage();
			forwardPath = RUNNTIME_EXCEPTION;
			ex.printStackTrace();
		}
		
		request.setAttribute(ResponseFields.ERROR_CODE, errorCode);
		request.setAttribute(ResponseFields.ERROR_MSG, errorMsg);

		return new ModelAndView("forward:/" + forwardPath);
	}
	
}
