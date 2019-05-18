package com.joyance.springdemo.interfaces;

public interface MyBeanFactory {

     public Object getBean(String name);
     
     public <T> T getBean(Class<T> clazz);
}

