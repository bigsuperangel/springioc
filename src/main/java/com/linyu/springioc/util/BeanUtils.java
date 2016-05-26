package com.linyu.springioc.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtils {

	public static Method getWriteMethod(Object beanObj, String name) {
		// TODO Auto-generated method stub
		Method md = null;
		try {
			BeanInfo info = Introspector.getBeanInfo(beanObj.getClass());
			PropertyDescriptor[]  pds = info.getPropertyDescriptors();
			if(pds!=null){
				for (PropertyDescriptor pd : pds) {
					String pname = pd.getName();
					if(pname.equals(name)){
						md = pd.getWriteMethod();
					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md;
	}

}
