package com.victorjussiani.pizzeria.test.base;

import models.pizzeria.Pizza;

public class MockObjectBuilder {

	public static Pizza createPizza(){
		Pizza pizza = new Pizza();
		pizza.setNome("Calabresa");
		pizza.setValue(new Long("19"));
		pizza.save();
		
		return pizza;
	}
}