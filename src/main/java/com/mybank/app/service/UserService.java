package com.mybank.app.service;

import java.util.List;

import com.mybank.app.entity.User;

public interface UserService {
	
	List<User> findAll();
	User save(User user);

}
