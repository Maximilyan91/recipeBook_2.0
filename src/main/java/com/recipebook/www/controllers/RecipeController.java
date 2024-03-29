package com.recipebook.www.controllers;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody Recipe recipe) {
        long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipe(id);

        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.editRecipe(id, recipe);

        if (newRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.deleteRecipe(id);

        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
        Map<Long, Recipe> allRecipes = recipeService.getAllRecipes();

        if (allRecipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allRecipes);
    }

    @PostMapping("/byIngredient/{id}")
    public ResponseEntity<Map<Long, Recipe>> findRecipeByIngredientId(@PathVariable long id) {
        Map<Long, Recipe> recipes = recipeService.findRecipeByIngredient(id);

        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }
@PostMapping("/byIngredients")
    public ResponseEntity<Map<Long, Recipe>> findRecipeByIngredients(@RequestBody List<Ingredient> ingredients) {
        Map<Long, Recipe> recipes = recipeService.findRecipeByIngredients(ingredients);

        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }
}
