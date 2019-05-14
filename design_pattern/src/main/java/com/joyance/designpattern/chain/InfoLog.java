package com.joyance.designpattern.chain;

public class InfoLog extends AbstractLogger{

	int level = AbstractLogger.info;
	
	@Override
	public void writeMessage(String message) {
		System.out.println("info log "+message);	
	}

}
