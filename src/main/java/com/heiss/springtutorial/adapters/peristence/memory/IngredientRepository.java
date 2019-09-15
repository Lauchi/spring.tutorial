package com.heiss.springtutorial.adapters.peristence.memory;

import com.heiss.springtutorial.domain.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IngredientRepository {
    public static List<Ingredient> Ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla",Ingredient.IngredientType.WRAP),
                new Ingredient("COTO", "Corn Tortilla",Ingredient.IngredientType.WRAP),

                new Ingredient("GRBF", "Ground Beef",Ingredient.IngredientType.PROTEIN),
                new Ingredient("CARN", "Carnitas",Ingredient.IngredientType.PROTEIN),

                new Ingredient("TMTO", "Diced Tomatoes",Ingredient.IngredientType.VEGGIES),
                new Ingredient("LETC", "Lettuce",Ingredient.IngredientType.VEGGIES),

                new Ingredient("CHED", "Cheddar",Ingredient.IngredientType.CHEESE),
                new Ingredient("JACK", "Monterrey Jack",Ingredient.IngredientType.CHEESE),

                new Ingredient("SLSA", "Salsa",Ingredient.IngredientType.SAUCE),
                new Ingredient("SRCR", "Sour Cream",Ingredient.IngredientType.SAUCE)
        );

    public static Ingredient byId(String ingredientId) {
        Stream<Ingredient> ingredientStream = Ingredients.stream().filter(i -> i.getId().equals(ingredientId));
        Optional<Ingredient> first = ingredientStream.findFirst();
        return first.isPresent() ? first.get() : null;
    }

    public static List<Ingredient> byType(Ingredient.IngredientType ingredientType) {
        List<Ingredient> ingredientsByType = Ingredients.stream()
                .filter(x -> x.getIngredientType().equals(ingredientType))
                .collect(Collectors.toList());
        return ingredientsByType;
    }
}
