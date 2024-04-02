package com.recipebook.www.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String title;

    private int cookingTime;

    private List<Ingredient> ingredientList;

    private List<String> cookingSteps;

}
