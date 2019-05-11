package com.joyance.basedemo.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.joyance.basedemo.mybatis.mapper.ConfigMapper;
import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.parser.MapperProxy;
import com.joyance.basedemo.mybatis.parser.Parser;
import com.joyance.basedemo.mybatis.persistence.Config;

public class MybatisTest {

	public static void main(String[] args) {
    	Parser parser = new Parser();
    	Configuration config = parser.parse();
    	System.out.println(JSON.toJSONString(config,true));
    	
    	MapperProxy proxys = new MapperProxy(config);
    	ConfigMapper configMapper =proxys.getMapper(ConfigMapper.class);
    	Config result = configMapper.selectById(14);
    	System.out.println(JSON.toJSONString(result,true));
	}
}
