package com.taylor.taylorapp.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taylor.taylorapp.entities.Order;
import com.taylor.taylorapp.model.OrderInfo;

@Repository("OrderInfoRepo")
public interface OrderInfoRepo  extends CrudRepository<Order, String>{
	
	@Query(value ="Select new com.taylor.taylorapp.model.OrderInfo (ord.id, ord.orderDate, ord.orderNum, ord.amount, ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) from Order ord ORDER BY ord.orderNum desc")
	OrderInfo findByOrderNumOrderByOrderNumDesc();

}
