package com.heiss.springtutorial.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class IngredientTest {

    @Test
    public void createDeployment() {
        Ingredient ingredient = new Ingredient("id", "Apple", Ingredient.IngredientType.VEGGIES);

        Assert.assertEquals("Apple", ingredient.getName());
    }
}
