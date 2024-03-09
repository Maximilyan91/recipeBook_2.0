package com.recipebook.www.service.impl;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.IngredientService;
import com.recipebook.www.service.RecipeService;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static long id = 0;
    private final Map<Long, Recipe> recipes = new HashMap<>();

    private final IngredientService ingredientService;

    ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService, IngredientService ingredientService) {
        this.validationService = validationService;
        this.ingredientService = ingredientService;
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

    @Override
    public Map<Long, Recipe> findRecipeByIngredient(long id) {

        if (id < 1) {
            throw new IllegalArgumentException("введен отрицательный id");
        }

        Map<Long, Ingredient> ingredients = ingredientService.getAllIngredients();
        Ingredient ingredient = ingredients.get(id);
        Map<Long, Recipe> resultMap = new TreeMap<>();
        long recipeId = 0;

        for (Recipe recipe : recipes.values()) {
            if (recipe.getIngredientList().get((int) id).equals(ingredient)) {
                resultMap.put(++recipeId, recipe);
            }
        }
        return resultMap;
    }

    @Override
    public Map<Long, Recipe> findRecipeByIngredients(List<Ingredient> ingredients) {

        Map<Long, Recipe> resultMap = new TreeMap<>();
        long id = 0;

        for (Recipe recipe : recipes.values()) {
            if (new HashSet<>(recipe.getIngredientList()).containsAll(ingredients)) {
                resultMap.put(++id, recipe);
            }
        }
        return resultMap;
    }
}
