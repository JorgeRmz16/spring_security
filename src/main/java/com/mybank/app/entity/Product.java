package com.mybank.app.entity;

import com.mybank.app.validation.IsExistDb;
import com.mybank.app.validation.IsRequired;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	//@IsRequired
	@IsExistDb
	private String sku;
	
	@NotEmpty(message="{NotEmpty.product.name}")
	@Size(min=3, message="{Min.Product.price}", max=20)
	private String name;
	
	//@Pattern //para trabajar con expreciones regulares
	@Min(500) // valor minimo
	@NotNull(message=("{NotNull.product.price}"))
	private Integer price;
	
	//@NotBlank(message = ("{NotBlank.product.description}")) // valida que no sea solo un espacio
	@IsRequired // anotacion creada >> se puede agregar mensaje personalizado en messages.propierties
	private String description;
}
