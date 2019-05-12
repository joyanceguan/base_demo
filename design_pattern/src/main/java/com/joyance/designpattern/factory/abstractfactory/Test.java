package com.joyance.designpattern.factory.abstractfactory;

public class Test {

	public static void main(String[] args) {
		
		ITProductFactory factory = new Apple();
		String computer = factory.productComputer().work();
		String telephone = factory.productTelephone().call();
		System.out.println(computer);
		System.out.println(telephone);
		factory = new Xiaomi();
	    computer = factory.productComputer().work();
	    telephone = factory.productTelephone().call();
	    System.out.println(computer);
		System.out.println(telephone);
	}
}
