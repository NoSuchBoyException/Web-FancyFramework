package org.fancy.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil implements ApplicationContextAware {

	private static volatile BeanUtil instance;
	private static ApplicationContext applicationContext;

	private BeanUtil() {}

	public static BeanUtil getInstance() {
		BeanUtil ins = instance;
		if (null == ins) {
			synchronized (BeanUtil.class) {
				if (null == ins) {
					ins = new BeanUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		BeanUtil.applicationContext = applicationContext;
	}

	public Object getBean(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getBean(name);
	}
	
	public Object getBean(String name, Object[] params)
			throws NoSuchBeanDefinitionException {
		
		return applicationContext.getBean(name, params);
	}
	
	public void clear() {
		applicationContext = null;
	}

}
