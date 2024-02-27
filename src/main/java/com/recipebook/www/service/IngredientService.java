package com.recipebook.www.service;

import com.recipebook.www.model.Ingredient;

public interface IngredientService {

    void add(Ingredient ingredient);

    Ingredient get(int id);
}
