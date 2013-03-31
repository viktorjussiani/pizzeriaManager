package com.victorjussiani.pizzeria.controllers;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.victorjussiani.pizzeria.models.Ingredient;
import com.victorjussiani.pizzeria.models.Pizza;

public class PizzaController extends Controller{

	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAllPizzas(){
		List<Pizza> pizzas = Pizza.findAll();
		
		return ok(Json.toJson(pizzas));
	}
	
	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getPizzaById(){
		JsonNode json = request().body().asJson();
		Long pizzaId = json.findPath("id").getLongValue();
		
		Pizza pizza = Pizza.findById(pizzaId);
		
		return ok(Json.toJson(pizza));
	}
	
	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getIngredientByName(){
		JsonNode json = request().body().asJson();
		String pizzaName = json.findPath("name").getTextValue();
		
		List<Ingredient> pizzas = Ingredient.findByName(pizzaName);
		
		return ok(Json.toJson(pizzas));
	}
}
