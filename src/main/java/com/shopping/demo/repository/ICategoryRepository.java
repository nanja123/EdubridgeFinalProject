package com.shopping.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.demo.entities.Category;


public interface ICategoryRepository extends CrudRepository<Category,Integer>{
	

}
