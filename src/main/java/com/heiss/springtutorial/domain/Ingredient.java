package com.heiss.springtutorial.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final IngredientType ingredientType;

    public enum IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}


