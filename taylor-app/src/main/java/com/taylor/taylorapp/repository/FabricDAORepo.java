package com.taylor.taylorapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.Fabrics;
import com.taylor.taylorapp.entities.Product;

public interface FabricDAORepo  extends CrudRepository<Fabrics, String>{
	
	Page<Fabrics> findAll(Pageable paging);

}
