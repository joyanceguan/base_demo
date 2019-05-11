package com.joyance.basedemo.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {

	//sql语句 
	private String sql;
	
	//参数名称
	private List<String> paramNames = new ArrayList<String>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<String> getParamNames() {
		return paramNames;
	}

	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}
	
}
