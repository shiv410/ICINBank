package com.icin.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.User;
import com.icin.repository.UserRepository;
import com.icin.response.RegisterResponse;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository dao;
	

	public RegisterResponse createAccount(User user) {
		RegisterResponse response = new RegisterResponse();
		boolean flag = true;
		String message = "Registration Succesful";
		if (this.EmailAlreadyExists(user.getEmail())) {
			message = "Email already Exists";
			flag = false;
		}

		if (this.PhoneAlreadyExists(user.getPhone())) {
			message = "Phone number already Exists";
			flag = false;
		}

		if (this.usernameAlreadyExists(user.getUsername())) {
			message = "Username already Exists";
			flag = false;
		}

		if (flag) {
			String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(hashedPassword);
			this.dao.save(user);
		}

		response.setRegistrationStatus(flag);
		response.setResponseMessage(message);
		response.setUsername(user.getUsername());
		return response;
	}

	public boolean usernameAlreadyExists(String username) {
		try {
			User u = this.dao.findByUsername(username);
			System.out.println(u.toString());
			return true;
		} catch (Exception var3) {
			return false;
		}
	}

	public boolean EmailAlreadyExists(String email) {
		try {
			User u = this.dao.findByEmail(email);
			System.out.println(u.toString());
			return true;
		} catch (Exception var3) {
			return false;
		}
	}

	public boolean PhoneAlreadyExists(long l) {
		try {
			User u = this.dao.findByPhone(l);
			System.out.println(u.toString());
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

}
