package com.joyance.designpattern.factory.abstractfactory;

public class Xiaomi implements ITProductFactory{

	public Telephone productTelephone() {
		return new XiamoMiTelephone();
	}

	public Computer productComputer() {
		return new XiaomiComputer();
	}

}
