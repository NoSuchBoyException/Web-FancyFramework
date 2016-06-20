package org.fancy.framework.helpers;

import javax.servlet.http.HttpServletRequest;

import org.fancy.framework.services.AbstractService;
import org.fancy.framework.utils.BeanUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.ObjectUtils;

/**
 * Used to get auth strategy and related entity
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class AuthHelper {

	private static volatile AuthHelper instance;
	private static BeanUtil beanUtil = BeanUtil.getInstance();
	
	private AuthHelper() {}
	
	public static AuthHelper getInstance() {
		AuthHelper ins = instance;
		if (null == ins) {
			synchronized (AuthHelper.class) {
				if (null == ins) {
					ins = new AuthHelper();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * get the auth strategy for the giving request
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @return the auth strategy
	 * @throws Exception 
	 */
	public AbstractService getAuthStrategy(HttpServletRequest request)
			throws Exception {
		
		return (AbstractService) getAuthFactor(request, null, true);
	}

	/**
	 * get the token entity to auth for the giving request
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @param params
	 *            the parameters used to build entity
	 * @return the token entity to auth
	 * @throws Exception
	 */
	public Object getTokenEntity(HttpServletRequest request, Object params)
			throws Exception {

		return getAuthFactor(request, params, false);
	}

	/**
	 * get auth factor(contains auth strategy and token entity) for the giving
	 * request
	 * 
	 * @param request
	 *            HttpServletRequest from client
	 * @param params
	 *            the parameters used to build factor bean
	 * @param getStrategy
	 *            find and return strategy bean if true, otherwise find and
	 *            return entity bean
	 * @return the specific auth factor
	 * @throws Exception 
	 */
	
	private Object getAuthFactor(HttpServletRequest request, Object params,
			boolean getStrategy) throws Exception {
		
		String servletPath = request.getServletPath();
		String requestName = servletPath.substring(1, servletPath.length());
		
		if ("login".equalsIgnoreCase(requestName)) {
			return beanUtil.getBean("emptyAuthService");
		} else {
			if (getStrategy) {
				return getBean(requestName + "AuthService",
						"defaultAuthService", null);
			} else {
				return getBean(requestName + "TokenEntity",
						"defaultTokenEntity", new Object[] {request, params});
			}
		}
	}

	private Object getBean(String name, String defaultName, Object[] params)
			throws Exception {
		
		Object bean;
		
		if (ObjectUtils.isEmpty(params)) {
			try {
				bean = beanUtil.getBean(name);
			} catch (NoSuchBeanDefinitionException e) {
				bean = beanUtil.getBean(defaultName);
			}
		} else {
			try {
				bean = beanUtil.getBean(name, params);
			} catch (NoSuchBeanDefinitionException e) {
				bean = beanUtil.getBean(defaultName, params);
			}
		}
		
		if (bean instanceof Exception) {
			throw (Exception) bean;
		} else {
			return bean;
		}
	}

}
