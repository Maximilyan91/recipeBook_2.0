package com.recipebook.www.service.impl;

import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.RecipeService;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static long id = 0;
    private final Map<Long, Recipe> recipes = new HashMap<>();
    ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public long addRecipe(Recipe recipe) {

        if (!validationService.validate(recipe)) {
            throw new IllegalArgumentException("Некорректный рецепт");
        }
        recipes.put(++id, recipe);
        return id;
    }

    @Override
    public Recipe getRecipe(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id отрицателен или равен 0");
        }
        return recipes.get(id);
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new IllegalArgumentException("Некорректный рецепт");
        }

        if (!recipes.containsKey(id)) {
            throw new IllegalArgumentException(("Рецепт с id " + id + " отсутствует в базе"));
        }

        recipes.put(id, recipe);
        return recipe;
    }

    @Override
    public Recipe deleteRecipe(long id) {
        if (!recipes.containsKey(id)) {
            throw new IllegalArgumentException(("Рецепт с id " + id + " отсутствует в базе"));
        }
        return recipes.remove(id);
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        if (recipes.isEmpty()) {
            throw new EmptyStackException();
        }
        return recipes;
    }
}
