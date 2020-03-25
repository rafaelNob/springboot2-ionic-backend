package com.example.demo.service.validatiors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

//@Constraint(validatedBy = Cliente.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
	
	String message() default "Erro de validação";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}
