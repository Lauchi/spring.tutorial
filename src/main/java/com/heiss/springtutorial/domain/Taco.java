package com.heiss.springtutorial.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private long Id;
    private Date CreatedAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String tacoName;

    @NotNull(message = "You must choose at least 2 ingredient")
    @Size(min=2, message="You must choose at least 2 ingredient")
    private List<String> tacoIngredients;
}
