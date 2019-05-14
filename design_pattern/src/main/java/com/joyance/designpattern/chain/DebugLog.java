package com.joyance.designpattern.chain;

public class DebugLog extends AbstractLogger{
	
    int level = AbstractLogger.debug;

	@Override
	public void writeMessage(String message) {
        System.out.println("debug log "+message);		
	}

}
