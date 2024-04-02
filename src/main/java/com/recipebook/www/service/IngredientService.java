package com.recipebook.www.service;

import com.recipebook.www.model.Ingredient;
import jakarta.annotation.PostConstruct;

import java.util.Map;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(long id);

    Map<Long, Ingredient> getAllIngredients();

    void saveToFile();

    Map<Long, Ingredient> readFromFile();

    @PostConstruct
    void init();
}
