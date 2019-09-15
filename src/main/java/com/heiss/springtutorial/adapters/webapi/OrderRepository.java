package com.heiss.springtutorial.adapters.webapi;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static List<TacoOrder> orders = new ArrayList<>();

    public static void add(TacoOrder order) {
        orders.add(order);
    }

    public static List<TacoOrder> getAll() {
        return orders;
    }
}
