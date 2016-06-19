package org.fancy.framework.aspects.impl;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.fancy.framework.aspects.AbstractAspect;
import org.fancy.framework.daos.AbstractDao;
import org.fancy.framework.entities.LogBean;
import org.fancy.framework.helpers.LogAdapter;
import org.fancy.framework.utils.BeanUtil;

/**
 * The service log aspect which used to wave into all service object and log
 * all business service
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class ServiceLogAspect extends AbstractAspect {
	
	private static LogAdapter logAdapter = LogAdapter.getInstance();
	private static BeanUtil beanUtil = BeanUtil.getInstance();
	
	@Override
	protected Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		super.doAround(pjp);
		
		AbstractDao logDao = (AbstractDao) beanUtil.getBean("logDao");
		HttpServletRequest request = (HttpServletRequest) pjp.getArgs()[0];
		Object entity = pjp.getArgs()[1];
		
		// log the request info
		LogBean requestLogBean = logAdapter.adaptRequest(request, entity);
		logDao.execute(new Object[] {requestLogBean});
		
		// do business service
		Object result = pjp.proceed();
		
		// log the response info
		LogBean responseLogBean = logAdapter.adaptResponse(request, result);
		logDao.execute(new Object[] {responseLogBean});
		return result;
	}

}
