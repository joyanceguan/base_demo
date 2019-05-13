package com.joyance.designpattern.observer;

public class Client1 implements Observer{

	public void update(String message) {
		System.out.println("客户端1接到通知:"+message);
	}

	public static void main(String[] args) {
		NodeImpl node = new NodeImpl();
		Node n = node;
		ByObeserver o = node;
		Observer observer1 = new Client1();
		Observer observer2 = new Client2();
		o.addObserver(observer1);
		o.addObserver(observer2);
		n.addNode();
		n.deleteNode();
	}
}
