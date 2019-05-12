package com.joyance.designpattern.factory.simple;

public class AnimalFactory {

	public Animal createAnimal(Class<?> clazz){
		Animal animal = null;
		if(clazz.equals(Cat.class)){
			animal = new Cat();
		}else if(clazz.equals(Dog.class)){
			animal = new Dog();
		}
		return animal;
	}
	
	public static void main(String[] args) {
		AnimalFactory factory = new AnimalFactory();
		String words = factory.createAnimal(Dog.class).say();
		System.out.println(words);
	}
}
