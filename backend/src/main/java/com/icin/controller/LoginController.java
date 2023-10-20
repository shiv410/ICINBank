package com.icin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icin.details.LoginDetails;
import com.icin.response.LoginResponse;
import com.icin.service.LoginService;

@RestController
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
public class LoginController {

	@Autowired
	LoginService service;

	public LoginController() {
	}

	@PostMapping({ "/login" })
	public LoginResponse userLogin(@RequestBody LoginDetails details) {
		return this.service.customerLogin(details);
	}

}
