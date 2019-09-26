package com.heiss.springtutorial.adapters.webapi;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TacoOrder {
    public static TacoOrder Create(String name, String street, String city, String state, String ccNumber) {
        TacoOrder tacoOrder = new TacoOrder();

        tacoOrder.setId(UUID.randomUUID());
        tacoOrder.setPlacedAt(LocalDateTime.now());

        tacoOrder.setName(name);
        tacoOrder.setStreet(street);
        tacoOrder.setCity(city);
        tacoOrder.setState(state);
        tacoOrder.setCcNumber(ccNumber);

        return tacoOrder;
    }

    private UUID id;
    private LocalDateTime placedAt;
    private UUID tacoId;

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
