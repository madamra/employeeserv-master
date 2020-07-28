package com.paypal.bfs.test.employeeserv.annatation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface FieldValidator {

	String message() default "";

	boolean required() default false;

	int min() default 0;

	int max() default 0;

	String regex() default "";

}

