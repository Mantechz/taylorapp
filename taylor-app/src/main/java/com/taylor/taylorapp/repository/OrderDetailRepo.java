package com.taylor.taylorapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.OrderDetail;

public interface OrderDetailRepo extends CrudRepository<OrderDetail, String> {
	Optional<OrderDetail> findById(String id);
	
	
	

}
