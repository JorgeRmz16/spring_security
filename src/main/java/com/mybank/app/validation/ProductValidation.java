package com.mybank.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mybank.app.entity.Product;

public class ProductValidation implements Validator{
	// validacion de datos sin anotaciones, se maneja en productController y no se necesitan en la clase product
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override			// objeto product BindError
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "no puede estar vacio!");
		
		// 1 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "NotBlank.product.description");
		if(product.getDescription() == null || product.getDescription().isBlank()) {
			errors.rejectValue("description", null,  "es requerido");
		}
		
		if(product.getPrice() == null) {
			errors.rejectValue("price", null, "se requiere");
		}else if(product.getPrice() < 500) {
			errors.rejectValue("price", null, "minimo 500");
		}
	}

}
