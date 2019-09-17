package com.heiss.springtutorial.adapters.peristence.memory;

import com.heiss.springtutorial.application.IIngredientRepository;
import com.heiss.springtutorial.domain.Ingredient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void saveAndGetById() {
        ingredientRepository.save(new Ingredient("PGM", "Pig Meat", Ingredient.IngredientType.PROTEIN));
        Ingredient flour = ingredientRepository.findOne("PGM");

        Assert.assertEquals(flour.getId(), "PGM");
        Assert.assertEquals(flour.getName(), "Pig Meat");
        Assert.assertEquals(flour.getIngredientType(), Ingredient.IngredientType.PROTEIN);
    }

    @Test
    public void byType() {

        Iterable<Ingredient> ingredients = ingredientRepository.byType(Ingredient.IngredientType.WRAP);
        Iterator<Ingredient> iterator = ingredients.iterator();
        Ingredient next = iterator.next();
        iterator.next();

        Assert.assertEquals(Ingredient.IngredientType.WRAP, next.getIngredientType());
        Assert.assertEquals(false, iterator.hasNext());
    }
}
