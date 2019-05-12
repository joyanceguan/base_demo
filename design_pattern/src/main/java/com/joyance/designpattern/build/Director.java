package com.joyance.designpattern.build;

import com.alibaba.fastjson.JSON;

public class Director {
	
   public static Product build(){
	   Builder builder = ConcreteBuilder.create();
	   Product product = builder
	        .buildFoot("black foot")
	        .buildLeg("gray leg")
	        .buildBody("yello body")
	        .buildArm("blue arm")
	        .buildHand("red hand")
	        .buildHead("green head").build();
	   
	   System.out.println(JSON.toJSONString(product, true));
	   return product;
   }
   
   public static void main(String[] args) {
	   Director.build();
   }
}
