package com.joyance.basedemo.mybatis.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;

import com.joyance.basedemo.mybatis.impl.StatementBuilderImpl;
import com.joyance.basedemo.mybatis.interfaces.StatementBuilder;
import com.joyance.basedemo.mybatis.model.Configuration;
import com.joyance.basedemo.mybatis.model.Constants;
import com.joyance.basedemo.mybatis.model.JDBCConfig;
import com.joyance.basedemo.mybatis.model.MyStatement;
import com.joyance.basedemo.mybatis.utils.DocumentReader;

/**
 * Configuration处理器
 * 获取配置文件信息
 * @author guanyue
 */
public class ConfigurationParser {

	//解析配置文件
    public Configuration parse(){
    	Configuration configuration = new Configuration();
    	//读取配置文件
    	Document document = getDocByPath("global.xml");
    	Element root = document.getRootElement();
    	//解析environments，为了获取jdbc
    	parseEnvironments(root,configuration);
    	//解析mapper
    	setMappers(root,configuration);
    	return configuration;
    }
    
    //根据路径获取document
    private Document getDocByPath(String path){
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
    	DocumentReader documentReader = new DocumentReader();
    	Document document = documentReader.createDocument(inputStream);
    	return document;
    }
    
    
    //解析environments，为了获取jdbc
    @SuppressWarnings("unchecked")
	public void parseEnvironments(Element root,Configuration configuration){
    	Element elementDefault= root.element("environments");
    	String defaultEnvId = elementDefault.attributeValue("default");
    	// 获取所有的environment标签对象
        List<Element> elements = elementDefault.elements();
        for (Element envElement : elements) {
        	String envId = envElement.attributeValue("id");
        	if (defaultEnvId.equals(envId)) {
        		// 获取数据源信息
        		Element dataSourceElement = envElement.element("dataSource");
        		setJDBC(dataSourceElement,configuration);
        	}
        }
    }
    
    //获取jdbc
    @SuppressWarnings("unchecked")
	public void setJDBC(Element dataSourceElement,Configuration configuration){
    	// 获取连接池类型
		String dataSourceType = dataSourceElement.attributeValue("type");
		// 获取连接属性信息
		List<Element> propertyElements = dataSourceElement.elements("property");
		Properties properties = new Properties();
		for (Element propertyElement : propertyElements) {
			String name = propertyElement.attributeValue("name");
			String value = propertyElement.attributeValue("value");
			properties.setProperty(name, value);
		}
        // 设置连接信息
		if (dataSourceType.equals("DBCP")) {
			JDBCConfig dataSource = new JDBCConfig();
			dataSource.setDriver(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			configuration.setJdbc(dataSource);
		}
    }
    
    //获取mappers
    @SuppressWarnings("unchecked")
	private void setMappers(Element root,Configuration configuration){
    	Element mappers= root.element("mappers");
    	List<Element> elements = mappers.elements();
    	Map<String,MyStatement> map = new ConcurrentHashMap<String,MyStatement>();
    	List<Class<?>> list = new ArrayList<Class<?>>();
    	for(Element mapper : elements){
    		//获取resource的配置文件路径
    		String resource = mapper.attributeValue("resource");
    		Document document = getDocByPath(resource);
    		parseMapper(document,map,list);
    	}
    	configuration.setStatementMap(map);
    	//获取需要动态代理的接口
    	configuration.setNamespaces(list);
    }
    
    //获取statement
    @SuppressWarnings("unchecked")
	private void parseMapper(Document document,Map<String,MyStatement> map,List<Class<?>> list){
    	Element root = document.getRootElement();
    	String namespace = root.attributeValue("namespace");
    	try {
    		//将需要动态代理的接口加入
			list.add(Class.forName(namespace));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
    	List<Element> statements = root.elements();
    	for(Element statement:statements){
    		String id = statement.attributeValue("id");
    		String statementId = String.format(Constants.statementIdFormat, namespace,id);
    		//如果已经家在过相同的方法则不再加载
    		if(map.containsKey(statementId)){
    			continue;
    		}
    		StatementBuilder builder = StatementBuilderImpl.create();
    		MyStatement myStatement = builder.id(id)
    		       .type(statement.getName())
    		       .parameterType(statement.attributeValue("parameterType"))
    		       .resultType(statement.attributeValue("resultType"))
    		       .sql(statement.getText())
    		       .build();
    		map.put(statementId, myStatement);
    	}
    }
    
    
}
