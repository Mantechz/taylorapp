package com.taylor.taylorapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taylor.taylorapp.entities.Notification;

public interface UserNotificationRepo  extends CrudRepository<Notification, Long>{
	List<Notification> findByEmail(String Email);

}
