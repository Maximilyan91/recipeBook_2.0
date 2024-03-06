package com.recipebook.www.service.impl;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.service.IngredientService;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Map<Long, Ingredient> ingredients = new HashMap<>();

    private static long id = 0;

    ValidationService validation;

    public IngredientServiceImpl(ValidationService validation) {
        this.validation = validation;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (!validation.validate(ingredient)) {
            throw new IllegalArgumentException("Неверный ингредиент");
        }
        ingredients.put(++id, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("id отрицателен или равен 0");
        }
        return ingredients.get(id);
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (!validation.validate(ingredient)) {
            throw new IllegalArgumentException("Неверный ингредиент");
        }

        if (!ingredients.containsKey(id)) {
            throw new IllegalArgumentException(("Ингредиент с id " + id + " отсутствует в базе"));
        }
        ingredients.put(id, ingredient);
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (!ingredients.containsKey(id)) {
            return false;
        }
        ingredients.remove(id);
        return true;
    }

}
