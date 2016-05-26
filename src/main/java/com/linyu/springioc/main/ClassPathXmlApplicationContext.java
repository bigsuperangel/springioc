package com.linyu.springioc.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.linyu.springioc.config.Bean;
import com.linyu.springioc.config.Property;
import com.linyu.springioc.parse.ConfigManager;
import com.linyu.springioc.util.BeanUtils;

public class ClassPathXmlApplicationContext implements BeanFactory{
	
	private Map<String,Bean> config;
	private Map<String,Object> context = new ConcurrentHashMap<String, Object>();

	@Override
	public Object getBean(String name) {
		// TODO Auto-generated method stub
		return context.get(name);
	}
	
	public ClassPathXmlApplicationContext(String path) {
		// TODO Auto-generated constructor stub
		config = ConfigManager.getConfig(path);
		if(config != null){
			for (Entry<String, Bean> en : config.entrySet()) {
				String beanName = en.getKey();
				Bean bean = en.getValue();
				Object existBean = context.get(beanName);
				if(existBean==null){
					Object beanObj = createBean(bean);
					context.put(beanName,beanObj);
				}
			}
		}
	}

	private Object createBean(Bean bean)  {
		// TODO Auto-generated method stub
		String className = bean.getClassName();
		System.out.println(className);
		Class clazz = null;
		try {
			 clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("className配置错误");
		}
		Object beanObj =null;
		try {
			 beanObj = clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("没有空参构造");
		} 
		
		if(bean.getProperites()!=null){
			for (Property prop : bean.getProperites()) {
				String value = prop.getValue();
				String name = prop.getName();
				Method setMethod = BeanUtils.getWriteMethod(beanObj,name);
				Object param = null;
				if(prop.getValue()!=null){
					param = value;
				}
				
				if(prop.getRef()!=null){
					Object existBean = context.get(prop.getRef());
					if(existBean==null){
						existBean = createBean(config.get(prop.getRef()));
						context.put(prop.getRef(), existBean);
					}
					param = existBean;
				}
				try {
					setMethod.invoke(beanObj, param);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return beanObj;
	}

}
