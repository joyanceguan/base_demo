package com.joyance.designpattern.agency;

public class Tenant implements User{

    private Agency agency;
    
    private String name;

    public Tenant(Agency agency,String name){
    	this.agency = agency;
    	this.name = name;
    }

	public void sendMessage(String message) {
		System.out.println("租户"+name+"发送了消息:"+message);
		agency.constact(message, this);
	}

	public void ReceiveMessage(String message) {
		System.out.println("租户"+name+"接收到消息："+ message);
	}

}
