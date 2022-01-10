package com.shopping.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException(ProductNotFoundException exception) {
		
		ModelAndView mv = new ModelAndView("/views/error.jsp");
		
		mv.addObject("errorTitle", "Product not available!");
		
		mv.addObject("errorDescription", "The product you are looking for is not available right now!");
		
		mv.addObject("title", "Product Unavailable");
		
		return mv;
	}
}
