package org.fancy.framework.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {

	private static PropertiesUtil instance;
	private static Properties properties;
	
	private PropertiesUtil() {}

	public static PropertiesUtil getInstance() {
		PropertiesUtil ins = instance;
		if (null == ins) {
			synchronized (PropertiesUtil.class) {
				if (null == ins) {
					ins = new PropertiesUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		
		super.processProperties(beanFactoryToProcess, props);
		properties = props;
	}
	
	public String getString(String name) {
		return properties.getProperty(name);
	}

	public int getInt(String name) {
		return Integer.parseInt(getString(name));
	}

	public List<String> getList(String name, String regex) {
		return Arrays.asList(getString(name).split(regex));
	}
	
	public void clear() {
		properties.clear();
		properties = null;
	}
	
}
