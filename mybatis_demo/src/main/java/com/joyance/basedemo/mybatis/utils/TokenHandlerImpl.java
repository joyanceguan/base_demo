package com.joyance.basedemo.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

public class TokenHandlerImpl implements TokenHandler{

	List<String> fieldNames = new ArrayList<String>();
	
	public String handleToken(String content) {
		fieldNames.add(content);
		System.out.println(content+"====");
		return "?";
	}
	
	public List<String> getFieldNames(){
		return fieldNames;
	}

}
