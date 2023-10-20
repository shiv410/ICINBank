package com.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.User;
import com.icin.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public User findByUsername(String name) {
		return null;
	}

	public User findByFullname(String name) {
		return userRepo.findByUsername(name);
	}

}
