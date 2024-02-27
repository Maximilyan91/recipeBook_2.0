package com.recipebook.www.service.impl;

import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.RecipeService;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static int id = 0;
    private final Map<Integer, Recipe> recipes = new HashMap<>();
    ValidationService validationService;

    @Override
    public void addRecipe(Recipe recipe) {

        if (!validationService.validate(recipe)) {
            throw new IllegalArgumentException("Неверный рецепт");
        }
        recipes.put(++id, recipe);
    }

    @Override
    public Recipe getRecipe(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("id отрицателен или равен 0");
        }
        return recipes.get(id);
    }
}
