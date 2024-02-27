package com.recipebook.www.model;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {

    private String title;

    private int cookingTime;

    private List<Ingredient> ingredientList;

    private List<String> cookingSteps;

    private String measureUnit;
}
