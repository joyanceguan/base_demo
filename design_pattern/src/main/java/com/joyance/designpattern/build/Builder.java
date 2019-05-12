package com.joyance.designpattern.build;

public interface Builder {
	
	public Builder buildFoot(String feet);

	public Builder buildLeg(String legs);
	
	public Builder buildBody(String body);
	
	public Builder buildArm(String arms);
	
	public Builder buildHand(String hands);
	
	public Builder buildHead(String head);
	
	public Product build();
}
