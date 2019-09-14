package com.heiss.springtutorial.domain;

import lombok.Data;

import java.util.List;

@Data
public class Taco {
    private String tacoName;
    private List<String> tacoIngredients;
}
