package com.taylor.taylorapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taylor.taylorapp.entities.Order;

@Repository("OrderRepository")
public interface OrderRepository extends CrudRepository<Order, String>{
	

	
	

	
	Order findByCustomerEmail(String email);
	
	
	
    
}
