package com.victorjussiani.pizzeria.controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Pizzeria extends Controller {
  
  public static Result index() {
	  return ok(views.html.pizzeria.index.render());
  }
}