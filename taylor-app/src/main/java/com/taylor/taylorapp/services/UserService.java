package com.taylor.taylorapp.services;


import com.taylor.taylorapp.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
   
}
