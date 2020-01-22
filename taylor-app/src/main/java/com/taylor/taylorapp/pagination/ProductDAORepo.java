package com.taylor.taylorapp.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.model.ProductInfo;

public interface ProductDAORepo extends PagingAndSortingRepository<Product, String>{
	
	 
	Page<Product> findAll(Pageable paging);
	
	org.hibernate.query.Query<ProductInfo> findByNameLike(String name);

}
