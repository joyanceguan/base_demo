package com.joyance.designpattern.build;

public class ConcreteBuilder implements Builder{
	
	private ConcreteBuilder(){}
	
	private Product product;

	public ConcreteBuilder buildFoot(String feet) {
		product.setFoot(feet);
		return this;
	}

	public ConcreteBuilder buildLeg(String legs) {
		product.setLeg(legs);
		return this;
	}

	public ConcreteBuilder buildBody(String body) {
		product.setBody(body);
		return this;
	}

	public ConcreteBuilder buildArm(String arms) {
		product.setArm(arms);
		return this;
	}

	public ConcreteBuilder buildHand(String hands) {
		product.setHand(hands);
		return this;
	}

	public ConcreteBuilder buildHead(String head) {
		product.setHead(head);
		return this;
	}

    public static Builder create(){
    	ConcreteBuilder cb = new ConcreteBuilder();
    	cb.product = new Product();
    	return cb;
    }
    
    public Product build(){
    	return product;
    }
}
