package com.taylor.taylorapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.OrderDetail;

public interface CartLineInfo extends CrudRepository<OrderDetail, String> {

}
