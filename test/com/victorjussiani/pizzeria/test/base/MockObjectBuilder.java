package com.victorjussiani.pizzeria.test.base;

import com.victorjussiani.pizzeria.models.Pizza;

import play.db.jpa.Transactional;

public class MockObjectBuilder {

	@Transactional
	public static Pizza createPizza(){
		Pizza pizza = new Pizza();
		pizza.setNome("Calabresa");
		pizza.setValue(new Long("19"));

		return pizza;
	}
}