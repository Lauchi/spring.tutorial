package com.heiss.springtutorial.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TacoTest {

    @Test
    public void createDeployment() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Zwiebel");

        Taco ingredient = new Taco();
    }
}
