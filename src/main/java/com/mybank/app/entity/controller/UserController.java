package com.mybank.app.entity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mybank.app.entity.User;
import com.mybank.app.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appi/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> list(){
		return userService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

	}
	
	private ResponseEntity<?> validation(BindingResult result){
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
}
