package com.taylor.taylorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taylor.taylorapp.entities.Userprofile;


@Repository("userprofilesrepo")
public interface userprofilesrepo  extends CrudRepository<Userprofile, Long>{
	Userprofile findByUsername(String username);
	

}
