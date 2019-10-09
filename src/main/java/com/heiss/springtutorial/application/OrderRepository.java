package com.heiss.springtutorial.application;

import com.heiss.springtutorial.domain.TacoOrder;
import com.heiss.springtutorial.domain.Taco;

import java.util.List;

public interface OrderRepository {
    Taco findCurrentOrder();
    long save(TacoOrder ingredient);
    List<TacoOrder> getAll();
}