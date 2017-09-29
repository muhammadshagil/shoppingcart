package com.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.store.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
