package com.taylor.taylorapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.Fabrics;


public interface FabricRepository extends CrudRepository<Fabrics, String>{
	Fabrics findByCode(String code);

	
}
