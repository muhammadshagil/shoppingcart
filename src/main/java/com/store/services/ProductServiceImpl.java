package com.store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.model.Product;
import com.store.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
    private ProductRepository productRepository;

	@Autowired
    public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); 
        return products;
    }

    @Override
    public Product getById(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }
    @Override
    public void deleteAll(){
    	productRepository.deleteAll();
    }
}
