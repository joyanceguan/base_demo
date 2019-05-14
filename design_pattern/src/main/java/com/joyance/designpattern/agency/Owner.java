package com.joyance.designpattern.agency;

public class Owner implements User{
	
    private Agency agency;
    
    private String name;
    
    public Owner(Agency agency,String name){
    	this.agency = agency;
    	this.name = name;
    }

	public void sendMessage(String message) {
		System.out.println("房主"+name+"发送了消息:"+message);
		agency.constact(message, this);
	}

	public void ReceiveMessage(String message) {
		System.out.println("房主"+name+"接收到消息："+ message);
	}

}
