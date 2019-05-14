package com.joyance.designpattern.share;

public class Test {

	public static void main(String[] args) {
		Circle circle = ShapeFactory.getCircle("green");
		circle.setX(1);
		circle.setY(1);
		circle.setRadius(2);
		circle.draw();
		
		Circle circle2 = ShapeFactory.getCircle("green");
		System.out.println(circle+"+"+circle2);
	}
	
}
