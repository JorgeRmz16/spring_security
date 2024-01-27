package com.mybank.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.mybank.app.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
}
