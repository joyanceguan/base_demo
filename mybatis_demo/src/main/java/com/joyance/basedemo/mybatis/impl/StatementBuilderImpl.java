package com.joyance.basedemo.mybatis.impl;

import com.joyance.basedemo.mybatis.interfaces.StatementBuilder;
import com.joyance.basedemo.mybatis.model.BoundSql;
import com.joyance.basedemo.mybatis.model.MyStatement;
import com.joyance.basedemo.mybatis.model.Operate_Type;
import com.joyance.basedemo.mybatis.utils.GenericTokenParser;
import com.joyance.basedemo.mybatis.utils.TokenHandler;
import com.joyance.basedemo.mybatis.utils.TokenHandlerImpl;
import com.mysql.jdbc.StringUtils;

public class StatementBuilderImpl implements StatementBuilder{
	
	private MyStatement statement;

	public StatementBuilder id(String id) {
		statement.setId(id);
		return this;
	}

	public StatementBuilder resultType(String resultType) {
		try {
			if(!StringUtils.isNullOrEmpty(resultType))
			   /*TODO 基本类型，不需要完全限定类名*/
			   statement.setResultType(Class.forName(resultType));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public StatementBuilder parameterType(String parameterType) {
		try {
			if(!StringUtils.isNullOrEmpty(parameterType))
				/*TODO 基本类型，不需要完全限定类名*/
			   statement.setParameterType(Class.forName(parameterType));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public StatementBuilder type(String type) {
		statement.setType(Operate_Type.getType(type));
		return this;
	}

	public StatementBuilder sql(String sql) {
		statement.setBoundSql(getBoundSql(sql));
		return this;
	}

	public MyStatement build() {
		return statement;
	}

	private StatementBuilderImpl(){};
	
	
	public static StatementBuilder create(){
		StatementBuilderImpl builder = new StatementBuilderImpl();
		builder.statement = new MyStatement();
		return builder;
	}

	
	private BoundSql getBoundSql(String sql){
    	BoundSql boundSql = new BoundSql();
    	TokenHandler tokenHandler = new TokenHandlerImpl();
    	GenericTokenParser parser = new GenericTokenParser("#{","}",tokenHandler);
    	boundSql.setParamNames(tokenHandler.getFieldNames());
		String str = parser.parse(sql);
		boundSql.setSql(str);
		return boundSql;
    }
}
