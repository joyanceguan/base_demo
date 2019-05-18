package com.joyance.springdemo.model;

public class Property {

	private String name;
	
	private ValueRef type;
	
	private String value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ValueRef getType() {
		return type;
	}

	public void setType(ValueRef type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
