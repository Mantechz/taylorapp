package com.taylor.taylorapp.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.taylor.taylorapp.entities.User;


@Repository("Userfetch")
public interface Userfetch extends CrudRepository<User, Long> {
    User findByUsername(String name);




}





