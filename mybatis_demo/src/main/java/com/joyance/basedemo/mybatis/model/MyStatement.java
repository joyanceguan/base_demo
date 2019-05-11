package com.joyance.basedemo.mybatis.model;

public class MyStatement {

	private String id;
	private Class<?> resultType;
	private Class<?> parameterType;
	private Operate_Type type;
	private BoundSql boundSql;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Class<?> getResultType() {
		return resultType;
	}
	public void setResultType(Class<?> resultType) {
		this.resultType = resultType;
	}
	public Class<?> getParameterType() {
		return parameterType;
	}
	public void setParameterType(Class<?> parameterType) {
		this.parameterType = parameterType;
	}
	public Operate_Type getType() {
		return type;
	}
	public void setType(Operate_Type type) {
		this.type = type;
	}
	public BoundSql getBoundSql() {
		return boundSql;
	}
	public void setBoundSql(BoundSql boundSql) {
		this.boundSql = boundSql;
	}
}
