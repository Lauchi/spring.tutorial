package com.heiss.springtutorial.application;

import com.heiss.springtutorial.adapters.webapi.TacoOrder;
import com.heiss.springtutorial.domain.Taco;

public interface OrderRepository {
    Taco findCurrentOrder();
    void save(TacoOrder ingredient);
    Iterable<TacoOrder> getAll();
}