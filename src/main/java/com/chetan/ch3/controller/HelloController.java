package com.chetan.ch3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chetan.ch3.model.User;

@RestController
public class HelloController {
	
	@Autowired
	JdbcUserDetailsManager userDetailsManager;

	@GetMapping("/hello")
	public String hello() {
		return "Hello Developer..!";
	}
	
	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		userDetailsManager.createUser(user);
	}
	
}
