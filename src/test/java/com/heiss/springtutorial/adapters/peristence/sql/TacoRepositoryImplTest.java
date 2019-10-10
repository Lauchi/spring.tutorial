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
        List<String> tacoIngredients = new ArrayList<>();
        String ingredient = "CHED";
        tacoIngredients.add(ingredient);
        Taco taco = new Taco();
        taco.setTacoName("Fieses TEil");
        taco.setTacoIngredients(tacoIngredients);

        tacoRepository.save(taco);
        tacoRepository.save(taco);

        Iterable<Taco> all = tacoRepository.findAll();
        List<Taco> collect = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        Assert.assertEquals(2, collect.size());
        Taco tacoReloaded = collect.get(0);
        Assert.assertEquals(taco.getTacoName(), tacoReloaded.getTacoName());
        List<String> ingredients = StreamSupport.stream(tacoReloaded.getTacoIngredients().spliterator(), true).collect(Collectors.toList());
        Assert.assertEquals(1, ingredients.size());
        Assert.assertEquals(ingredient, ingredients.get(0));
    }

    @Test
    public void getById() {
        Taco taco = new Taco();
        taco.setTacoName("Fieses TEil");
        taco.setTacoIngredients(new ArrayList<>());

        long tacoId = tacoRepository.save(taco);
        Taco tacoFromDb = tacoRepository.findOne(tacoId);
        Assert.assertEquals(taco.getTacoName(), tacoFromDb.getTacoName());
    }
}