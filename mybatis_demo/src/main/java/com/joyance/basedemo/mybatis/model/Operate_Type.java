package com.joyance.basedemo.mybatis.model;

public enum Operate_Type {
	SELECT,
	INSERT,
	UPDATE,
	DELETE;
	
	public static Operate_Type getType(String value){
		if("select".equals(value)){
			return SELECT;
		}else if("insert".equals(value)){
			return INSERT;
		}else if("update".equals(value)){
			return UPDATE;
		}else if("delete".equals(value)){
			return DELETE;
		}
		return null;
	}
}
