package com.mybank.app.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.app.entity.Role;
import com.mybank.app.entity.User;
import com.mybank.app.repository.RoleRepository;
import com.mybank.app.repository.UserRepository;
import com.mybank.app.service.UserService;


@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly=true)
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
	
	@Transactional
	@Override
	public User save(User user) {
		Optional<Role> optionalRoleUsere = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		
		optionalRoleUsere.ifPresent(roles::add);
		
		if(user.isAdmin()) {
			Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
			optionalRoleAdmin.ifPresent(roles::add);
		}
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword())); // codificacion del password
		return userRepository.save(user);
	}

	@Override
	public boolean existByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

}
