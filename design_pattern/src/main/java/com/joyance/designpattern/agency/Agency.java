package com.joyance.designpattern.agency;

import java.util.List;

public interface Agency {

	public void constact(String message,User user);
	
	public void addOwner(User owner); 
	
	public void addTenant(User tenant);
}
