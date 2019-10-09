package com.heiss.springtutorial.application;

import com.heiss.springtutorial.domain.Taco;

public interface TacoRepository {
    Iterable<Taco> findAll();
    Taco findOne(long id);
    long save(Taco ingredient);
}