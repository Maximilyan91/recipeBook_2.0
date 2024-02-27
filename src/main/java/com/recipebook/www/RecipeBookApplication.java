package com.recipebook.www;

import com.recipebook.www.model.Ingredient;
import com.recipebook.www.model.Recipe;
import com.recipebook.www.service.RecipeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RecipeBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeBookApplication.class, args);
	}

}
