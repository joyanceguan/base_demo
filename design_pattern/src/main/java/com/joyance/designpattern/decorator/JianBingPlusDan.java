package com.joyance.designpattern.decorator;

public class JianBingPlusDan extends StandardJianBing{
	
	private JianBing jianbing;
	
	public JianBingPlusDan(JianBing jianbing){
		this.jianbing = jianbing;
	}

	public String desc() {
		return jianbing.desc() + " + 蛋";
	}

	public int price() {
		return jianbing.price() + 2;
	}

}
