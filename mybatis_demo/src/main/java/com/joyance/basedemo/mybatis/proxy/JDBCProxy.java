package com.joyance.basedemo.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.model.Constants;
import com.joyance.basedemo.mybatis.model.MyStatement;
import com.joyance.basedemo.mybatis.parser.JDBCParser;


public class JDBCProxy implements InvocationHandler{
	
	private Map<String,MyStatement> statementMap;
	
	private JDBCParser jdbcParser;
	
	public JDBCProxy(Map<String,MyStatement> statementMap,JDBCParser jdbcParser){
		this.statementMap = statementMap;
		this.jdbcParser = jdbcParser;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> clazz = method.getDeclaringClass();
		String methodName = method.getName();
		String statementId = String.format(Constants.statementIdFormat, clazz.getTypeName(),methodName);
		//获取执行的statement执行jdbc代码并返回
		MyStatement myStatement = statementMap.get(statementId);
		return jdbcParser.execute(myStatement, args, method.getReturnType());
	}

	@SuppressWarnings("unchecked")
	public static <T> T createObject(Class<T> classz,Map<String,MyStatement> statementMap,JDBCParser jdbcParser){
		ClassLoader classLoader = classz.getClassLoader();
		Class<?>[] interfaces = new Class[] { classz };
		JDBCProxy proxy = new JDBCProxy(statementMap,jdbcParser);
		T t = (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
		return t;
	}
	
	public static Map<Class<?>,Object> getTotalMapper(Configuration configuration,JDBCParser jdbcParser){
		Map<Class<?>,Object> totalMapper = new HashMap <Class<?>,Object>();
		List<Class<?>> namespaces = configuration.getNamespaces();
		Map<String, MyStatement> statementMap = configuration.getStatementMap();
		for(Class<?> claz:namespaces){
			totalMapper.put(claz, createObject(claz,statementMap,jdbcParser));
		}
		return totalMapper;
	}
	
}
