package com.recipebook.www.service;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.model.Recipe;

public interface ValidationService {

    boolean validate(Recipe recipe);

    boolean validate(Ingredient ingredient);
}