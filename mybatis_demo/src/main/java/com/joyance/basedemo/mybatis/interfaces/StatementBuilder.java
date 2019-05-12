package com.joyance.basedemo.mybatis.interfaces;

import com.joyance.basedemo.mybatis.model.MyStatement;

public interface StatementBuilder {

	public StatementBuilder id(String id);
	
	public StatementBuilder resultType(String resultType);
	
	public StatementBuilder parameterType(String parameterType);
	
	public StatementBuilder type(String type);
	
	public StatementBuilder sql(String sql);
	
	public MyStatement build();
}
