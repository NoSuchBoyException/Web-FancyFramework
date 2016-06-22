package org.fancy.framework.aspects.impl;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.fancy.framework.aspects.AbstractAspect;
import org.fancy.framework.daos.AbstractLogDao;
import org.fancy.framework.entities.AbstractEntity;
import org.fancy.framework.entities.LogEntity;
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
		
		AbstractLogDao logDao = (AbstractLogDao) beanUtil.getBean("logDao");
		HttpServletRequest request = (HttpServletRequest) pjp.getArgs()[0];
		AbstractEntity entity = (AbstractEntity) pjp.getArgs()[1];
		
		// log the request info
		LogEntity requestLogEntity = logAdapter.adaptRequest(request, entity);
		logDao.log(new Object[] {requestLogEntity});
		
		// do business service
		Object result = pjp.proceed(new Object[] {request, entity});
		
		// log the response info
		LogEntity responseLogEntity = logAdapter.adaptResponse(request, result);
		logDao.log(new Object[] {responseLogEntity});
		
		return result;
	}

}
