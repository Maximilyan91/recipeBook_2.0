package com.recipebook.www.service;

import com.recipebook.www.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public interface RecipeService {

    Map<Integer, Recipe> recipeList = new HashMap<>();

    long addRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    Recipe deleteRecipe(long id);

    Map<Long, Recipe> getAllRecipes();

    Map<Long, Recipe> findRecipeByIngredient(long id);
}
