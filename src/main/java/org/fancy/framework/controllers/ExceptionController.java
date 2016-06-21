package org.fancy.framework.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fancy.framework.constants.ErrorConsts;
import org.fancy.framework.helpers.TransHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller used to handle all exceptions thrown in project
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
@RestController
public class ExceptionController {

	private static TransHelper transHelper = TransHelper.getInstance();

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

		return transHelper.transResultResp(ErrorConsts.EC_BAD_REQUEST,
				ErrorConsts.MSG_BAD_REQUEST);
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

		return transHelper.transErrorResp(ErrorConsts.EC_SERVER_INTERNAL_ERROR,
				ErrorConsts.MSG_SERVER_INTERNAL_ERROR);
	}

}
