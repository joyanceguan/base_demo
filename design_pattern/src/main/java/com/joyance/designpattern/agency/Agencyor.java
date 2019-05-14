package com.joyance.designpattern.agency;

import java.util.ArrayList;
import java.util.List;

public class Agencyor implements Agency{
	
    List<User> owners = new ArrayList<User>();
    
    List<User> tenants = new ArrayList<User>();


	public void constact(String message, User user) {
		if(user instanceof Owner){
			for(User tenant:tenants){
				tenant.ReceiveMessage(message);
			}
		}else{
			for(User owner:owners){
				owner.ReceiveMessage(message);
			}
		}
	}

	public void addOwner(User owner) {
		owners.add(owner);
	}

	public void addTenant(User tenant) {
		tenants.add(tenant);
	}

}
