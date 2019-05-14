package com.joyance.designpattern.chain;

public class ErrorLog extends AbstractLogger{

	int level = AbstractLogger.error;
	
	@Override
	public void writeMessage(String message) {
		System.out.println("error log "+message);	
	}

}