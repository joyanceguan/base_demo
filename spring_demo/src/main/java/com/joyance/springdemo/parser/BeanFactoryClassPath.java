package com.joyance.springdemo.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.joyance.springdemo.bean.Address;
import com.joyance.springdemo.bean.Person;
import com.joyance.springdemo.interfaces.MyBeanFactory;
import com.joyance.springdemo.model.BeanDefinition;
import com.joyance.springdemo.model.Property;
import com.joyance.springdemo.model.ValueRef;
import com.joyance.springdemo.utils.TypeConvert;

public class BeanFactoryClassPath implements MyBeanFactory{
	
	private Map<String,BeanDefinition> beanDefinitions;
	
	private Map<String,Object> nameBeans = new ConcurrentHashMap<String, Object>();
	private Map<Class<?>,Object> typeBeans = new ConcurrentHashMap<Class<?>, Object>();
	
	public BeanFactoryClassPath(String url){
		DocumentReader reader = new DocumentReader();
		Document document = reader.getDocByPath(url);
		beanDefinitions = parseDocument(document);
		System.out.println(JSON.toJSONString(beanDefinitions,true));
	}
	
	@SuppressWarnings("unchecked")
	private Map<String,BeanDefinition> parseDocument(Document document){
		Map<String,BeanDefinition> map = new HashMap<String,BeanDefinition>();
		List<Element> elements = document.getRootElement().elements();
		for(Element element : elements){
			BeanDefinition beanDefinition = new BeanDefinition();
			beanDefinition.setId(element.attributeValue("id"));
			if(element.attribute("init-method") != null){
				beanDefinition.setInitMethod(element.attributeValue("init-method"));
			}
			String claszz = element.attributeValue("class");
			try {
				beanDefinition.setClazz(Class.forName(claszz));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Element> propertieList = element.elements();
			List<Property> properties = new ArrayList<Property>();
			for(Element e : propertieList){
				Property property = new Property();
				property.setName(e.attributeValue("name"));
				property.setValue(e.attributeValue("value"));
				if(e.attribute("ref") != null){
					property.setType(ValueRef.REF);
					property.setValue(e.attributeValue("ref"));
				}else{
					property.setType(ValueRef.VALUE);
					property.setValue(e.attributeValue("value"));
				}
				properties.add(property);
			}
			beanDefinition.setProperties(properties);
			map.put(beanDefinition.getId(), beanDefinition);
		}
		return map;
	}

	public Object getBean(String name) {
		if(nameBeans.containsKey(name)){
			return nameBeans.get(name);
		}
		if(!beanDefinitions.containsKey(name)){
			return null;
		}
		BeanDefinition beanDefinition = beanDefinitions.get(name);
		Class<?> clazz = beanDefinition.getClazz();
		Object bean = null;
		try {
		     bean = clazz.newInstance();
		     List<Property> properties = beanDefinition.getProperties();
		     for(Property property : properties){
		    	 Field field = clazz.getDeclaredField(property.getName());
		    	 field.setAccessible(true);
		    	 if(property.getType() == ValueRef.VALUE){
		    		 field.set(bean, TypeConvert.setValue(field.getType(), property.getValue()));
		    	 }else{
		    		 field.set(bean, getBean(property.getName()));
		    	 }
		     }
		     if(beanDefinition.getInitMethod()!=null){
		    	//初始化bean
		    	 Method method = clazz.getDeclaredMethod(beanDefinition.getInitMethod(), null);
		    	 method.invoke(bean, null);
		     }
		     
		} catch (Exception e) {
			e.printStackTrace();
		}
		//放入缓存
		nameBeans.put(name, bean);
		if(!typeBeans.containsKey(clazz)){
			typeBeans.put(clazz, bean);
		}
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		if(typeBeans.containsKey(clazz)){
			return (T) typeBeans.get(clazz);
		}else{
			for(String name:beanDefinitions.keySet()){
				BeanDefinition beanDefinition = beanDefinitions.get(name);
				if(beanDefinition.getClazz() == clazz){
					return (T) getBean(beanDefinition.getId());
				}
			}
		}
		return null;
	}
	
    public static void main(String[] args) {
    	MyBeanFactory factory = new BeanFactoryClassPath("spring.xml");
    	Person person = (Person) factory.getBean("person");
    	Address address = factory.getBean(Address.class);
    	person.introduce();
    	System.out.println(JSON.toJSONString(address));
	}
}
