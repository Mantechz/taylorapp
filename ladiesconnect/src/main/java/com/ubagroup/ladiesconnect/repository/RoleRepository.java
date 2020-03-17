package com.ubagroup.ladiesconnect.repository;


import com.ubagroup.ladiesconnect.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
	
}