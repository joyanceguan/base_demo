package com.joyance.springdemo.model;

public enum ValueRef {

	VALUE(1,"value"),
	REF(2,"ref");
	
	private int value;
	private String desc;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private ValueRef(int value,String desc){
		this.value = value;
		this.desc = desc;
	}
	
}
