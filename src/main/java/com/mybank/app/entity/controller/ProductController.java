package com.mybank.app.entity.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mybank.app.entity.Product;
import com.mybank.app.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appi/products")
public class ProductController {
	@Autowired
	ProductService productService;
	
	//@Autowired
	//private ProductValidation productValidation;
	
	@GetMapping("/all")
	public List<Product> list(){
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> viewDeteils(@PathVariable Long id) {
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping																// manejo de errores (justo despues del body a comprobar)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
		//productValidation.validate(product, result);
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,@PathVariable Long id){
		// productValidation.validate(product, result); para validar con clase productValidation
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Product> productOptional = productService.update(id, product);
		if(productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> productOptional = productService.delete(id);
		if(productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(err ->{
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});;			// llave, valor
		return ResponseEntity.badRequest().body(errors);
	}
}
