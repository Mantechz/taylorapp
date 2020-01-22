package com.taylor.taylorapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.taylor.taylorapp.entities.Product;
import com.taylor.taylorapp.model.ProductInfo;

public interface ProductInfoRepo  extends CrudRepository<Product, String> {
	
	@Query(value ="Select new com.taylor.taylorapp.model.ProductInfo (p.code, p.name, p.price) from Product p order by p.createDate desc") 
	org.hibernate.query.Query<ProductInfo> findByNameLikes();
	
	org.hibernate.query.Query<ProductInfo> findByNameLike(String name);

}
