package com.shopping.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.demo.entities.Product;
import com.shopping.demo.repository.IProductRepository;

@Service
public class IProductService {
	
	@Autowired
	IProductRepository productRepository;
	
	//find single product
	public Product get(int productId) {
		Optional<Product> product = productRepository.findById(productId);
		Product find = product.get();
		return find;
	}
	
	public List<Product> list() {
		return (List<Product>) productRepository.findAll();
	}
	
	public List<Product> activeProducts(int active){
		List<Product> products = productRepository.listActiveProducts(active);
		return products;
	}
	
	public List<Product> activeProductsByCategory(int active,int categoryId){ 
		List<Product> category = productRepository.listActiveProductsByCategory(active, categoryId);
		return category;
	} 
	
	public int getCountActiveProducts(int count){
		List<Product> list=productRepository.getLatestActiveProducts(count);
		return list.size();
	}

}
