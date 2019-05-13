package com.joyance.designpattern.observer;

public class Client2 implements Observer{

	public void update(String message) {
		System.out.println("客户端2接到通知:"+message);
	}

}
