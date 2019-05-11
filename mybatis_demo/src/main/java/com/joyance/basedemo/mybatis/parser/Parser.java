package com.joyance.basedemo.mybatis.parser;

import com.alibaba.fastjson.JSON;
import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.proxy.MapperProxy;

/**
 * 总处理器
 * 获取所有dao对应的代理
 * @author guanyue
 */
public class Parser {

	public static MapperProxy getMapperProxy(){
		ConfigurationParser parser = new ConfigurationParser();
    	Configuration configuration = parser.parse();
    	System.out.println(JSON.toJSONString(configuration,true));
    	MapperProxy proxys = new MapperProxy(configuration);
    	return proxys;
	}
}
