package com.joyance.springdemo.utils;


public class TypeConvert {

	public static Object setValue(Class<?> clazz,String value){
		if(value == null || value.equals("")){
			return null;
		}
		String simpleName = clazz.getSimpleName();
		if(simpleName.equals("int")){
			return Integer.parseInt(value);
		}
		if(simpleName.equals("String")){
			return value;
		}
		return null;
	}
}
