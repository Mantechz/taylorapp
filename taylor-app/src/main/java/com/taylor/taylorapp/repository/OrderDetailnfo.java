package com.taylor.taylorapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.OrderDetail;
import com.taylor.taylorapp.model.OrderDetailInfo;

public interface OrderDetailnfo  extends CrudRepository<OrderDetail, String>{
	@Query(value = "Select new com.taylor.taylorapp.model.OrderDetailInfo (d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) from OrderDetail d where d.order.id = ?1")
	List<OrderDetailInfo> findOrder(String name);
	OrderDetail findByOrdernum(int id);

}
