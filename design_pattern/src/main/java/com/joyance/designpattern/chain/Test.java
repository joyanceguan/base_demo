package com.joyance.designpattern.chain;

public class Test {

	public static void main(String[] args) {
		AbstractLogger debug = new DebugLog();
		AbstractLogger info = new InfoLog();
		AbstractLogger error = new ErrorLog();
		
		error.setNextLogger(info);
		info.setNextLogger(debug);
		
		error.log(AbstractLogger.error, "出error了");
		info.log(AbstractLogger.info, "打info了");
	}
}
