package com.victorjussiani.pizzeria.test;


import org.junit.Assert;
import org.junit.Test;

import play.db.jpa.JPA;

import com.victorjussiani.pizzeria.models.Pizza;
import com.victorjussiani.pizzeria.test.base.BaseModelTest;
import com.victorjussiani.pizzeria.test.base.MockObjectBuilder;

public class PizzaTest extends BaseModelTest {
	
	@Test
	public void createPizza() {
		JPA.withTransaction(new play.libs.F.Callback0() {
			public void invoke() throws Throwable {
				setRollBackOnly();

				Pizza pizza = MockObjectBuilder.createPizza();
				pizza.save();
				
				Pizza pizzaFounded = Pizza.findById(pizza.getId());

				Assert.assertNotNull(pizzaFounded);
			}
		});
	}
}
