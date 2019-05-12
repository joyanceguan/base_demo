package com.joyance.designpattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class Prototype implements Cloneable,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Prototype deepClone() {
		Prototype prototype = null;
        try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            // 将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            prototype = (Prototype) ois.readObject();
       } catch (IOException e) {
           e.printStackTrace();
      } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return prototype;
   }

	public static void main(String[] args) throws CloneNotSupportedException {
		Prototype p1 = new  Prototype();
		p1.setId(1);
		p1.setName("joy");
		Person p = new Person();
		p.setName("joy");
		p.setSex(1);
		p1.setPerson(p);
		
		Prototype p2 =  (Prototype) p1.clone();
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(JSON.toJSONString(p2));
		System.out.println(p1.getPerson()+"_"+p2.getPerson());
		
		p2 = p1.deepClone();
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(JSON.toJSONString(p2));
		System.out.println(p1.getPerson()+"_"+p2.getPerson());
	}
	
}
