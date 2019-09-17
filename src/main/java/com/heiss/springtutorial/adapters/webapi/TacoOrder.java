package com.heiss.springtutorial.adapters.webapi;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TacoOrder {
    private Long id;
    private Date placedAt;

    @NotBlank(message="name is required")
    private String name;

    @NotBlank(message="Street is required")
    private String street;

    @NotBlank(message="City is required")
    private String city;

    @NotBlank(message="State is required")
    private String state;

    @NotBlank(message="ccNumber is required")
    private String ccNumber;
}
