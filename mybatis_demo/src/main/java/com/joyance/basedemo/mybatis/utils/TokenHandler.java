package com.joyance.basedemo.mybatis.utils;

import java.util.List;

public interface TokenHandler {
	String handleToken(String content);
	List<String> getFieldNames();
}
