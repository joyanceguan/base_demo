package com.joyance.basedemo.mybatis.model;

import java.util.List;
import java.util.Map;

public class Configuration {

	private JDBCConfig jdbc;
	
	private Map<String,MyStatement> statementMap;
	
	private List<Class<?>> namespaces;

	public JDBCConfig getJdbc() {
		return jdbc;
	}

	public void setJdbc(JDBCConfig jdbc) {
		this.jdbc = jdbc;
	}

	public Map<String, MyStatement> getStatementMap() {
		return statementMap;
	}

	public void setStatementMap(Map<String, MyStatement> statementMap) {
		this.statementMap = statementMap;
	}

	public List<Class<?>> getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(List<Class<?>> namespaces) {
		this.namespaces = namespaces;
	}
	
	
}
