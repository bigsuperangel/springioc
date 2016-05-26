package com.linyu.springioc.config;

import java.util.ArrayList;
import java.util.List;

public class Bean {
	private String name;
	private String className;
	private List<Property> properites = new ArrayList<Property>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Property> getProperites() {
		return properites;
	}
	public void setProperites(List<Property> properites) {
		this.properites = properites;
	}
	
	
}
