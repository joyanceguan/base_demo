package com.joyance.designpattern.share;

import com.alibaba.fastjson.JSON;

public class Circle implements Shape{

	private int x;
	private int y;
	private int radius;
	private String color;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void draw() {
		System.out.println(JSON.toJSONString(this));
	}

}
