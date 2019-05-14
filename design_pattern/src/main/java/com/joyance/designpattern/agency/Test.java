package com.joyance.designpattern.agency;

public class Test {

	public static void main(String[] args) {
		
		Agency agency = new Agencyor();
		User owner1 = new Owner(agency,"owner1");
		User tenant1 = new Tenant(agency,"tenant1");
		User owner2 = new Owner(agency,"owner2");
		User tenant2 = new Tenant(agency,"tenant2");
		agency.addOwner(owner1);
		agency.addTenant(tenant1);
		agency.addOwner(owner2);
		agency.addTenant(tenant2);
		
		tenant1.sendMessage("还有合适的房源吗");
		owner2.sendMessage("租房便宜了啊");
	}
}
