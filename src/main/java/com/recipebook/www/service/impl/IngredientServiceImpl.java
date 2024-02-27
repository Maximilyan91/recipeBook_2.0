package com.recipebook.www.service.impl;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.service.IngredientService;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Map<Integer, Ingredient> ingredients = new HashMap<>();

    private static int id = 0;

    ValidationService validation;

    @Override
    public void add(Ingredient ingredient) {
        if (!validation.validate(ingredient)) {
            throw new IllegalArgumentException("Неверный ингредиент");
        }
        ingredients.put(++id, ingredient);
    }

    @Override
    public Ingredient get(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("id отрицателен или равен 0");
        }
        return ingredients.get(id);
    }
}
