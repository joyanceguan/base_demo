package com.joyance.basedemo.mybatis.mapper;

import java.util.List;

import com.joyance.basedemo.mybatis.annotation.Param;
import com.joyance.basedemo.mybatis.persistence.Config;

public interface ConfigMapper {

	public int save(Config config);
	
	public Config selectById(@Param("id") Integer id);
	
	public int update(Config config);
	
	public List<Config> queryAll(Integer id);
	
	public int deleteById(Integer id);
}
