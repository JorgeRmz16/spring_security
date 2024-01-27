package com.mybank.app.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.app.entity.Product;
import com.mybank.app.repository.ProductRepository;
import com.mybank.app.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	ProductRepository productRespository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRespository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<Product> findById(Long id) {
		return productRespository.findById(id);
	}
	
	@Transactional
	@Override
	public Product save(Product product) {
		return productRespository.save(product);
	}
	
	@Transactional
	@Override
	public Optional<Product> update(Long id, Product product) {
		Optional<Product> productOptional = productRespository.findById(id);
		if(productOptional.isPresent()) {
			Product productDb = productOptional.orElseThrow();
			
			productDb.setSku(product.getSku());
			productDb.setName(product.getName());
			productDb.setDescription(product.getDescription());
			productDb.setPrice(product.getPrice());
			return Optional.of(productRespository.save(productDb));
		};
		return productOptional;
	}
	
	@Transactional
	@Override
	public Optional<Product> delete(Long id) {
		Optional<Product> productOptional = productRespository.findById(id);
		productOptional.ifPresent(productDb ->{
			productRespository.delete(productDb);			
		});
		return productOptional;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsBySku(String sku) {
		return productRespository.existsBySku(sku);
	}


}
