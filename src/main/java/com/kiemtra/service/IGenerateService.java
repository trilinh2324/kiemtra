package com.kiemtra.service;

import com.kiemtra.model.Product;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Optional<T> findById(int id);

    Product save(T t);

    void remove(int id);
}