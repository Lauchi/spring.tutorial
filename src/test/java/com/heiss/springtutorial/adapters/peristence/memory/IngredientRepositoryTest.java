package com.heiss.springtutorial.adapters.peristence.memory;

import com.heiss.springtutorial.domain.Ingredient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class IngredientRepositoryTest {

    @Test
    public void byId() {
        Ingredient flour = IngredientRepository.byId("FLTO");
        Assert.assertEquals(flour.getId(), "FLTO");
        Assert.assertEquals(flour.getName(), "Flour Tortilla");
        Assert.assertEquals(flour.getIngredientType(), Ingredient.IngredientType.WRAP);
    }

    @Test
    public void byType() {
        List<Ingredient> ingredients = IngredientRepository.byType(Ingredient.IngredientType.WRAP);
        Assert.assertEquals(2, ingredients.size());
    }
}
