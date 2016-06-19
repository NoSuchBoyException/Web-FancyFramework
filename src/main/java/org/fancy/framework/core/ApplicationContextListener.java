package org.fancy.framework.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.fancy.framework.utils.BeanUtil;
import org.fancy.framework.utils.PropertiesUtil;

public class ApplicationContextListener implements ServletContextListener {
		
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		PropertiesUtil.getInstance().clear();
		BeanUtil.getInstance().clear();
	}

}
