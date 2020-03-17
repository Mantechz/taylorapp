package com.ubagroup.ladiesconnect.services.impl;


import java.util.HashSet;

import com.ubagroup.ladiesconnect.entities.User;
import com.ubagroup.ladiesconnect.repository.UserRepository;
import com.ubagroup.ladiesconnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubagroup.ladiesconnect.repository.RoleRepository;


@Service
public class UserServiceImpl implements UserService {
    
	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public void save(User user) {
		
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
	

}
