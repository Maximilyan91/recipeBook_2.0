package com.recipebook.www.service;

import com.recipebook.www.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public interface RecipeService {

    Map<Integer, Recipe> recipeList = new HashMap<>();

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
