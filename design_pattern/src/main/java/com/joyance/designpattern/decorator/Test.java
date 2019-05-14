package com.joyance.designpattern.decorator;

public class Test {

	public static void main(String[] args) {
		JianBing simple = new StandardJianBing();
		System.out.println(simple.desc()+":"+simple.price());
		JianBing jianbing = new JianBingPlusChange(new JianBingPlusDan(simple)) ;
		System.out.println(jianbing.desc()+":"+jianbing.price());
	}
}
