package com.heiss.springtutorial.application;

import com.heiss.springtutorial.domain.Taco;

import java.util.UUID;

public interface TacoRepository {
    Iterable<Taco> findAll();
    Taco findOne(UUID id);
    void save(Taco ingredient);
}