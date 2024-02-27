package com.recipebook.www.model;

import lombok.Data;

@Data
public class Ingredient {

    private static int idGenerator;

    private String title;

    private int quantity;

    private String measureUnit;

    private int id;
}
