package com.linyu.springioc.parse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.linyu.springioc.config.Bean;
import com.linyu.springioc.config.Property;

public class ConfigManager {
	public static Map<String,Bean> getConfig(String path){
		Map<String,Bean> map = new HashMap<String, Bean>();
		
		SAXReader reader = new SAXReader();
		InputStream is = ConfigManager.class.getResourceAsStream(path);
		Document doc = null;
		try {
			 doc = reader.read(is);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("配置文件未找到");
		}
		String xpath = "//bean";
		List<Element> list = doc.selectNodes(xpath);
		if(list!=null){
			for (Element element : list) {
				Bean bean = new Bean();
				String name = element.attributeValue("name");
				String className = element.attributeValue("class");
				bean.setClassName(className);
				bean.setName(name);
				List<Element> result = element.elements("property");
				if(list!=null){
					for (Element child : result) {
						Property prop = new Property();
						String pname = child.attributeValue("name");
						String pvalue = child.attributeValue("value");
						String pref = child.attributeValue("ref");

						prop.setName(pname);
						prop.setValue(pvalue);
						prop.setRef(pref);
						bean.getProperites().add(prop);
					}
				}
				map.put(name, bean);
			}
		}
		return map;
	}
}
