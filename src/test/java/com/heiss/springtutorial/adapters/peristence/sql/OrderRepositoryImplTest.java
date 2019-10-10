package com.heiss.springtutorial.adapters.peristence.sql;


import com.heiss.springtutorial.domain.TacoOrder;
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

    @Autowired
    private TacoRepositoryImpl tacoRepository;

    @Test
    public void getAll() {
        Taco taco = new Taco();
        taco.setTacoName("Fieses TEil");
        taco.setTacoIngredients(new ArrayList<>());
        long tacoId = tacoRepository.save(taco);
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setTacoId(tacoId);
        tacoOrder.setName("Simon Heiss");
        tacoOrder.setStreet("Birkenstr. 4");
        tacoOrder.setCity("Pfinztal");
        tacoOrder.setState("BW");
        tacoOrder.setCcNumber("123456");

        long orderId = orderRepository.save(tacoOrder);
        Iterable<TacoOrder> all1 = orderRepository.getAll();
        List<TacoOrder> ingredients = StreamSupport.stream(all1.spliterator(), true).collect(Collectors.toList());
        Assert.assertEquals(1, ingredients.size());

        TacoOrder order = (TacoOrder) ingredients.toArray()[0];
        Assert.assertEquals(1, orderId);
        Assert.assertEquals(tacoOrder.getName(), order.getName());
        Assert.assertEquals(tacoOrder.getCity(), order.getCity());
        Assert.assertEquals(tacoOrder.getState(), order.getState());
        Assert.assertEquals(tacoOrder.getStreet(), order.getStreet());
        Assert.assertEquals(tacoOrder.getCcNumber(), order.getCcNumber());
    }
}