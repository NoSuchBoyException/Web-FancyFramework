package org.fancy.framework.aspects.impl;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.fancy.framework.aspects.AbstractAspect;
import org.fancy.framework.daos.AbstractDao;
import org.fancy.framework.entities.LogEntity;
import org.fancy.framework.helpers.LogAdapter;
import org.fancy.framework.utils.BeanUtil;

/**
 * The exception log aspect which used to wave into exception handler and log
 * all exception
 * 
 * @author Dongfan Yang
 * @time 2016年3月3日
 */
public class ExceptionLogAspect extends AbstractAspect {
	
	private static LogAdapter logAdapter = LogAdapter.getInstance();
	private static BeanUtil beanUtil = BeanUtil.getInstance();
	
	@Override
	protected void doBefore(JoinPoint jp) throws Exception {
		super.doBefore(jp);
		
		AbstractDao logDao = (AbstractDao) beanUtil.getBean("logDao");
		HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
		Exception ex = (Exception) jp.getArgs()[3];
		
		// log the exception info
		LogEntity exLogEntity = logAdapter.adaptException(request, ex);
		logDao.execute(new Object[] {exLogEntity});
	}
	
}
