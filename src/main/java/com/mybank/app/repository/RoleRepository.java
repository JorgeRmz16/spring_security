package com.mybank.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mybank.app.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
