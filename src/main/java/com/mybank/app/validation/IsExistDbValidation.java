package com.mybank.app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybank.app.service.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String>{
	
	@Autowired
	private ProductService productService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(productService == null) return true;
		
		return !productService.existsBySku(value);
	}

}
