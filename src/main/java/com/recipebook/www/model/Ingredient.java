package com.recipebook.www.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String title;

    private int quantity;

    private String measureUnit;
}
