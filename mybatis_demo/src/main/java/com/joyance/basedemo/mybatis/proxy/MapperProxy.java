package com.joyance.basedemo.mybatis.proxy;

import java.util.Map;

import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.parser.JDBCParser;
import com.joyance.basedemo.mybatis.proxy.JDBCProxy;

public class MapperProxy {

	private Map<Class<?>,Object> totalMapper;
	
	private JDBCParser jdbcParser;
	
	public MapperProxy(Configuration configuration){
		jdbcParser = new JDBCParser(configuration);
		totalMapper = JDBCProxy.getTotalMapper(configuration,jdbcParser);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<?> clazz){
		T mapper = (T) totalMapper.get(clazz);
		return mapper;
	}
}
