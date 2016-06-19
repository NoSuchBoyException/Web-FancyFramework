package org.fancy.framework.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.constants.FieldConsts.ResponseFields;
import org.fancy.framework.helpers.JSONHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The exception controller used to handle all exceptions thrown in project
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
@RestController
public class ExceptionController {

	private static JSONHelper jsonHelper = JSONHelper.getInstance();

	/**
	 * Handle checked exception
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @return checked exception info
	 */
	@RequestMapping("/checkedException")
	public Map<String, Object> handleCheckedException(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int errorCode = (int) request
				.getAttribute(ResponseFields.ERROR_CODE);
		String errorMsg = (String) request
				.getAttribute(ResponseFields.ERROR_MSG);

		return jsonHelper.buildResultResp(errorCode, errorMsg);
	}

	/**
	 * Handle runtime exception
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @return server internal error info
	 */
	@RequestMapping("/runtimeException")
	public Map<String, Object> handleRuntimeException(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return jsonHelper.buildErrorResp(
				ErrorConsts.EC_SERVER_INTERNAL_ERROR,
				ErrorConsts.MSG_SERVER_INTERNAL_ERROR);
	}

}
