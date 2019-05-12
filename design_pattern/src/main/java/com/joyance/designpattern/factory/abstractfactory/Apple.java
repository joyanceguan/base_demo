package com.joyance.designpattern.factory.abstractfactory;

public class Apple implements ITProductFactory{

	public Telephone productTelephone() {
		return new AppleTelephone();
	}

	public Computer productComputer() {
		return new AppleComputer();
	}
	
}
