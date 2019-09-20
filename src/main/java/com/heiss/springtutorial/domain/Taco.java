package com.heiss.springtutorial.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Data
public class Taco {
    private final UUID Id;
    private final LocalDateTime CreatedAt;

    public static Taco Create(String tacoName, Iterable<Ingredient> ingredients) {
        List<String> ingredientIds = StreamSupport.stream(ingredients.spliterator(), true)
                .map(Ingredient::getId).collect(Collectors.toList());
        return new Taco(UUID.randomUUID(), LocalDateTime.now(), tacoName, ingredientIds);
    }

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private final String tacoName;

    @NotNull(message = "You must choose at least 2 ingredient")
    @Size(min=2, message="You must choose at least 2 ingredient")
    private final Iterable<String> tacoIngredients;
}
