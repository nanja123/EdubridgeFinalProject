package com.shopping.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.demo.entities.Category;
import com.shopping.demo.entities.Product;
import com.shopping.demo.repository.IProductRepository;
import com.shopping.demo.service.ICategoryService;
import com.shopping.demo.service.IProductService;

@RestController

public class DataController {

	@Autowired
	IProductService productService;

	@GetMapping(path="/all/products/{active}")
	public List<Product> getAllProducts(@PathVariable int active){
		
		return productService.activeProducts(1);
	}
	
	@GetMapping(path="admin/all/products")
	public List<Product> getAllProductsForAdmin(){
		
		return productService.list();
	}


	@RequestMapping("/category/{active}/{id}")
	public List<Product> getProductsByCategory(@PathVariable int active,@PathVariable int id) {
		return productService.activeProductsByCategory(1, id);
	}
}
