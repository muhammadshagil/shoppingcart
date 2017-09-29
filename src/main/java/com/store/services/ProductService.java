package com.store.services;

import java.util.List;

import com.store.model.Product;

public interface ProductService {

    List<Product> listAll();

    Product getById(String id);
    Product saveOrUpdate(Product product);
    void deleteAll();
}
