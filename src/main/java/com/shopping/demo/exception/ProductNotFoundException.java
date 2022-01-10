package com.shopping.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public ProductNotFoundException() {
		this("Product is not available!");
	}
	
	public ProductNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
