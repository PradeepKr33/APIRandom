package com.example.apirandom.response;

import java.util.List;
public class MealResponse {

	private List<MealsItem> meals;

	public void setMeals(List<MealsItem> meals){
		this.meals = meals;
	}

	public List<MealsItem> getMeals(){
		return meals;
	}
}