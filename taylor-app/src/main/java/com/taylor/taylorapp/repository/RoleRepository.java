package com.taylor.taylorapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylor.taylorapp.entities.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
	
}