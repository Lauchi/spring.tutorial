package com.heiss.springtutorial.adapters.webapi;

import lombok.Data;

@Data
public class TacoOrder {
    private String name;
    private String street;
    private String city;
    private String state;
    private String ccNumber;
}
