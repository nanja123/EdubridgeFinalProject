package com.shopping.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.demo.entities.Category;
import com.shopping.demo.repository.ICategoryRepository;

@Service
public class ICategoryService {

	@Autowired
	ICategoryRepository categoryRepository;

	public List<Category> list() {
		return (List<Category>) categoryRepository.findAll();
	}

	public Category get(int id) {
		Optional<Category> category = categoryRepository.findById(id);
		Category find = category.get();
		return find;
	}
}
