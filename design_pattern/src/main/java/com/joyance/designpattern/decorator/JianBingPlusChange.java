package com.joyance.designpattern.decorator;

public class JianBingPlusChange extends StandardJianBing{

	private JianBing jianbing;
	
	public JianBingPlusChange(JianBing jianbing){
		this.jianbing = jianbing;
	}
	
	public String desc() {
		return jianbing.desc() + " + 烤肠";
	}

	public int price() {
		return jianbing.price() + 3;
	}

}
