package com.mybank.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mybank.app.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}
