package com.ubagroup.ladiesconnect.services;


import com.ubagroup.ladiesconnect.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
   
}
