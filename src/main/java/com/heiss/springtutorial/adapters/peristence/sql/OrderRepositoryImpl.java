package com.heiss.springtutorial.adapters.peristence.sql;

import com.heiss.springtutorial.adapters.webapi.TacoOrder;
import com.heiss.springtutorial.application.OrderRepository;
import com.heiss.springtutorial.domain.Taco;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Taco findCurrentOrder() {
        return null;
    }

    @Override
    public void save(TacoOrder ingredient) {

    }

    @Override
    public Iterable<TacoOrder> getAll() {
        return null;
    }
}
