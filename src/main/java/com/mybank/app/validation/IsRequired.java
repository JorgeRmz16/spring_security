package com.mybank.app.validation;

import java.lang.annotation.*;

import jakarta.validation.*;

@Constraint(validatedBy = RequiredValidation.class) // validación usando la classque lo implementa
@Retention(RetentionPolicy.RUNTIME) // La retención durante el tiempo de ejecución
@Target({ElementType.FIELD, ElementType.METHOD}) //  aplicable a campos y métodos
public @interface IsRequired {
	
	//de la class NotBlank
	String message() default "No puede contener espacios";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
