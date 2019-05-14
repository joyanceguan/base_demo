package com.joyance.designpattern.share;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {

	private static final Map<String,Circle> map = new HashMap<String,Circle>();
	
	public static Circle getCircle(String color){
		Circle circle;
		if(map.containsKey(color)){
			circle = map.get(color);
		}else{
			circle = new Circle();
			map.put(color, circle);
		}
		return circle;
	}
	
}
