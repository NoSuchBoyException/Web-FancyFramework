package org.fancy.framework.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.fancy.framework.entities.impl.UserEntity;
import org.fancy.framework.services.AbstractLoginService;
import org.fancy.framework.utils.BeanUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller used to handle all user request
 * 
 * @author Dongfan Yang
 * @time 2015年12月29日
 */
@RestController
public class UserController {

	private static BeanUtil beanUtil = BeanUtil.getInstance();
	
	@RequestMapping(value = "/login_v1.0", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request,
			@Valid @RequestBody UserEntity entity) throws Exception {
		
		return loginByVer(request, entity, "1.0");
	}

	// This method can called by all request mapping method, because it
	// dynamically generate service bean by request name and it's version,
	// and all service bean implemented the doJob method.
	@SuppressWarnings("unchecked")
	private Map<String, Object> loginByVer(HttpServletRequest request,
			UserEntity entity, String version) throws Exception {

		String servletPath = request.getServletPath();
		String requestName = servletPath.substring(1, servletPath.length());
		String serviceName = new StringBuilder()
				.append(requestName).append("Service_v").append(version)
				.toString();

		AbstractLoginService service = (AbstractLoginService) beanUtil
				.getBean(serviceName);

		return (Map<String, Object>) service.login(request, entity);
	}

}
