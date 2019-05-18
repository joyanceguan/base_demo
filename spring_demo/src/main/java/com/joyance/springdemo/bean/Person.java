package com.joyance.springdemo.bean;

public class Person {

	private String name;
	
	private int age;
	
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void init(){
		System.out.println("hello world");
	}

	public String introduce(){
		String message = "大家好，我叫"+name+",今年"+age+",来自于"+address.getProvince()+address.getCity();
		System.out.println(message);
	    return message;
	}
}
