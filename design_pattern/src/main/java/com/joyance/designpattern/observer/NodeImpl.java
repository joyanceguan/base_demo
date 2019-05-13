package com.joyance.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class NodeImpl implements Node,ByObeserver{

	private List<Observer> observers;
	
	public NodeImpl(){
		this.observers = new ArrayList<Observer>();
	}
	
	public int addObserver(Observer observer) {
		observers.add(observer);
		return 1;
	}

	public int removeObserver(Observer observer) {
		observers.remove(observer);
		return 1;
	}

	public int notifyObserver(String message) {
		for(Observer o:observers){
			o.update(message);
		}
		return observers.size();
	}

	public void addNode(){
		System.out.println("新增节点了");
		notifyObserver("新增节点");
	}

	public void deleteNode() {
		System.out.println("删除节点了");
		notifyObserver("删除节点");
	}
}
