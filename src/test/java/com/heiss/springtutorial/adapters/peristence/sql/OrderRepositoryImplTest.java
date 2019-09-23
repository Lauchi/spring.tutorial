package com.heiss.springtutorial.adapters.peristence.sql;


import com.heiss.springtutorial.adapters.webapi.TacoOrder;
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
public class OrderRepositoryImplTest {

    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Test
    public void getAll() {
        orderRepository.save(new TacoOrder());
        Iterable<TacoOrder> all1 = orderRepository.getAll();
        List<TacoOrder> ingredients = StreamSupport.stream(all1.spliterator(), true).collect(Collectors.toList());
        Assert.assertEquals(1, ingredients.size());
    }
}