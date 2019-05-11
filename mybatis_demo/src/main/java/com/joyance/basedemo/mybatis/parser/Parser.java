package com.joyance.basedemo.mybatis.parser;

import com.alibaba.fastjson.JSON;
import com.joyance.basedemo.mybatis.model.Configuration;

public class Parser {

	public static MapperProxy getMapperProxy(){
		ConfigurationParser parser = new ConfigurationParser();
    	Configuration configuration = parser.parse();
    	System.out.println(JSON.toJSONString(configuration,true));
    	MapperProxy proxys = new MapperProxy(configuration);
    	return proxys;
	}
}
