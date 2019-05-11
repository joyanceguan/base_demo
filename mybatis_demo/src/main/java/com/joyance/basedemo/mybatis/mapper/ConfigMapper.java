package com.joyance.basedemo.mybatis.mapper;

import com.joyance.basedemo.mybatis.persistence.Config;

public interface ConfigMapper {

	public int save(Config config);
	
	public Config selectById(Integer id);
	
}
