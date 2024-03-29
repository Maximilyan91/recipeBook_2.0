package com.recipebook.www.service.impl;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    public boolean validate(Recipe recipe) {
        return recipe != null
                && !recipe.getTitle().isBlank()
                && !recipe.getTitle().isEmpty()
                && !recipe.getCookingSteps().isEmpty()
                && !recipe.getIngredientList().isEmpty();
    }

    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && !ingredient.getTitle().isEmpty()
                && !ingredient.getTitle().isBlank()
                && !ingredient.getMeasureUnit().isEmpty()
                && !ingredient.getMeasureUnit().isBlank()
                && !(ingredient.getQuantity() < 0);
    }

}
