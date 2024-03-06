package com.recipebook.www.controllers;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.add(ingredient);

        if (newIngredient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(newIngredient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> get(@PathVariable long id) {
        Ingredient ingredient = ingredientService.get(id);

        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredient(id, ingredient);

        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        if (!ingredientService.deleteIngredient(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
