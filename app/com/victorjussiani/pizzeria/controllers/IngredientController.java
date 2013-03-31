package com.victorjussiani.pizzeria.controllers;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.victorjussiani.pizzeria.models.Ingredient;

public class IngredientController extends Controller{

	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getAllIngredients(){
		List<Ingredient> ingredients = Ingredient.findAll();
		
		return ok(Json.toJson(ingredients));
	}
	
	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getIngredientById(){
		JsonNode json = request().body().asJson();
		Long ingredientId = json.findPath("id").getLongValue();
		
		Ingredient ingredient = Ingredient.findById(ingredientId);
		
		return ok(Json.toJson(ingredient));
	}
	
	@Transactional(readOnly = true)
	@BodyParser.Of(play.mvc.BodyParser.Json.class)
	public static Result getIngredientByName(){
		JsonNode json = request().body().asJson();
		String ingredientName = json.findPath("name").getTextValue();
		
		List<Ingredient> ingredient = Ingredient.findByName(ingredientName);
		
		return ok(Json.toJson(ingredient));
	}
}
