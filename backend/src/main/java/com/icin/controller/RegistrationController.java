package com.icin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icin.entity.User;
import com.icin.response.RegisterResponse;
import com.icin.service.RegistrationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	public RegistrationController() {
	}

	@PostMapping({"/register" })
	public RegisterResponse createUser(@RequestBody User user) {
		return this.service.createAccount(user);
	}

}
