package com.joyance.designpattern.observer;

public interface ByObeserver {

    public int addObserver(Observer observer);
	
	public int removeObserver(Observer observer);
	
	public int notifyObserver(String message);
	
}
