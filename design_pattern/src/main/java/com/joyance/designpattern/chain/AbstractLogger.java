package com.joyance.designpattern.chain;

public abstract class AbstractLogger {
	
	public static final int debug = 1;
	public static final int info = 2;
	public static final int error = 3;

	protected int level;
	
	protected AbstractLogger nextLogger;
	
	public AbstractLogger getNextLogger() {
		return nextLogger;
	}

	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public abstract void writeMessage(String message);
	
	public void log(int level,String message){
		if(level > this.level){
			writeMessage(message);
		}
		if(nextLogger!=null){
			nextLogger.log(level, message);
		}
	}
	
}
