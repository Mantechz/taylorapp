package com.taylor.taylorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taylor.taylorapp.entities.Product;

@Repository("ProductRepository")
public interface ProductRepository extends CrudRepository<Product, String>{
	
	Product findByCode(String code);

}
