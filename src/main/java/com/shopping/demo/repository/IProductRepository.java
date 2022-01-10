package com.shopping.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shopping.demo.entities.Product;

public interface IProductRepository extends CrudRepository<Product,Integer> {
	

	@Query("FROM Product WHERE active=?1")
	List<Product> listActiveProducts(int active);
	@Query("FROM Product WHERE active=?1 and categoryId=?2")
	List<Product> listActiveProductsByCategory(int active,int categoryId);
	@Query("FROM Product WHERE active=?1 ORDER BY id")
	List<Product> getLatestActiveProducts(int count);

}
