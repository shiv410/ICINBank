package com.icin.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.details.UpdateDetails;
import com.icin.entity.User;
import com.icin.entity.UserDisplay;
import com.icin.repository.UserDisplayRepository;
import com.icin.repository.UserRepository;
import com.icin.response.UpdateResponse;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDisplayRepository userDisplayRepository;



	public UpdateResponse updateUser(UpdateDetails user) {
		boolean flag = true;
		UpdateResponse response = new UpdateResponse();
		String message = "Update successful";

		try {
			int counter = 0;
			User u = this.userRepository.findByUsername(user.getUsername());
			if (user.getAddress().length() != 0) {
				++counter;
				u.setAddress(user.getAddress());
			}

			if (user.getPassword().length() != 0 && user.getNewpassword().length() != 0) {
				++counter;
				String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
				u.setPassword(hashedPassword);
			}

			if (user.getEmail().length() != 0) {
				++counter;
				u.setEmail(user.getEmail());
			}

			if (user.getPhone() != 0L) {
				++counter;
				u.setPhone(user.getPhone());
			}

			System.out.println(counter);
			if (counter > 0) {
				this.userRepository.save(u);
			} else {
				flag = false;
				message = "Please enter some information to update";
			}
		} catch (Exception var8) {
			flag = false;
			response.setMessage("Update unsuccesful");
		}

		response.setMessage(message);
		response.setFlag(flag);
		return response;
	}

	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	public UserDisplay userDisplay(String username) {
		UserDisplay user = this.userDisplayRepository.getCurrentUser(username);
		return user;
	}

}
