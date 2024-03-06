package com.recipebook.www.service;

import com.recipebook.www.model.Ingredient;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(long id);
}
