package com.recipebook.www.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipebook.www.model.Ingredient;
import com.recipebook.www.service.IngredientsFileService;
import com.recipebook.www.service.IngredientService;
import com.recipebook.www.service.ValidationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Map<Long, Ingredient> ingredients = new HashMap<>();

    private static long id = 0;

    private final ValidationService validation;

    private final IngredientsFileService fileService;

    public IngredientServiceImpl(ValidationService validation, IngredientsFileService fileService) {
        this.validation = validation;
        this.fileService = fileService;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (!validation.validate(ingredient)) {
            throw new IllegalArgumentException("Неверный ингредиент");
        }
        ingredients.put(++id, ingredient);
        saveToFile();
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
        saveToFile();
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

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        if (ingredients.isEmpty()) {
            throw new EmptyStackException();
        }
        return ingredients;
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, Ingredient> readFromFile() {
        String json = fileService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    @Override
    @PostConstruct
    public void init() {
        ingredients = readFromFile();
        id = ingredients.size();
    }
}
