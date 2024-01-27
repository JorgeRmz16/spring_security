package com.mybank.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.mybank.app.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	boolean existsBySku(String sku);
}
