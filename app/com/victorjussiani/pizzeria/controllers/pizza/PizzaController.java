package com.victorjussiani.pizzeria.controllers.pizza;

import java.util.List;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.victorjussiani.pizzeria.models.Pizza;

public class PizzaController extends Controller{

	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAllPizzas(){
		List<Pizza> pizzas = Pizza.findAll();
		
		return ok(Json.toJson(pizzas));
	}
}
