package com.taylor.taylorapp.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taylor.taylorapp.entities.User;
import com.taylor.taylorapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
    	UserDetails userDetails = null;
        User user = userRepository.findByUsername(username);
        if (user == null) {
        	throw new UsernameNotFoundException("Username cannot be found");
        }
        
        userDetails = org.springframework.security.core.userdetails.User
        		.withUsername(user.getUsername())
        		.password(user.getPassword())
        		.accountExpired(false)
        		.accountLocked(false)
        		.credentialsExpired(false)
        		.authorities(user.getAuthorities())
        		.build();
        return userDetails;
    }


}
