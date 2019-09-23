package com.heiss.springtutorial.adapters.peristence.sql;


import com.heiss.springtutorial.domain.Ingredient;
import com.heiss.springtutorial.domain.Taco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class TacoRepositoryImplTest {

    @Autowired
    private TacoRepositoryImpl tacoRepository;

    @Test
    public void getAll() {
        ArrayList<Ingredient> tacoIngredients = new ArrayList<>();
        Ingredient e = new Ingredient("CHED", "Cheddar", Ingredient.IngredientType.CHEESE);
        tacoIngredients.add(e);
        Taco taco = Taco.Create("name", tacoIngredients);
        tacoRepository.save(taco);
        Iterable<Taco> all = tacoRepository.findAll();
        List<Taco> collect = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        Assert.assertEquals(1, collect.size());
        Assert.assertEquals(taco.getId(), collect.get(0).getId());
        Assert.assertEquals(taco.getTacoName(), collect.get(0).getTacoName());
        List<String> ingredients = StreamSupport.stream(collect.get(0).getTacoIngredients().spliterator(), true).collect(Collectors.toList());
        Assert.assertEquals(1, ingredients.size());
        Assert.assertEquals(e.getId(), ingredients.get(0));
    }

    @Test
    public void getById() {
        Taco taco = Taco.Create("name", new ArrayList<>());
        tacoRepository.save(taco);
        Taco tacoFromDb = tacoRepository.findOne(taco.getId());
        Assert.assertEquals(taco.getTacoName(), tacoFromDb.getTacoName());
        Assert.assertEquals(taco.getId(), tacoFromDb.getId());
    }
}