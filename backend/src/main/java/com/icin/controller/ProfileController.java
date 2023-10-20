package com.icin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icin.details.UpdateDetails;
import com.icin.entity.User;
import com.icin.entity.UserDisplay;
import com.icin.response.UpdateResponse;
import com.icin.service.ProfileService;

@RestController
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
public class ProfileController {

	@Autowired
	private ProfileService pservice;

	public ProfileController() {
	}

	@PutMapping({ "/profile/update" })
	public UpdateResponse updateUser(@RequestBody UpdateDetails user) {
		return this.pservice.updateUser(user);
	}

	@GetMapping({ "/profile/{username}" })
	public User getUser(@PathVariable("username") String username) {
		return this.pservice.getUser(username);
	}

	@GetMapping({ "home/{username}" })
	public UserDisplay userDisplay(@PathVariable("username") String username) {
		return this.pservice.userDisplay(username);
	}

}
