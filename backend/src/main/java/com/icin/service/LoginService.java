package com.icin.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.icin.details.LoginDetails;
import com.icin.entity.User;
import com.icin.repository.UserRepository;
import com.icin.response.LoginResponse;

@Service
public class LoginService {

	@Autowired
	private UserRepository uRepository;



	public LoginResponse customerLogin(LoginDetails login) {
		LoginResponse response = new LoginResponse();
		boolean flag = true;
		String message = "Login succesfull";
		User user = null;
		String hashedPassword = DigestUtils.sha256Hex(login.getPassword());

		try {
			user = this.uRepository.findByUsername(login.getUsername());
			if (user.getStatus()) {
				flag = false;
				message = "Dear Mr/Ms." + user.getFname() + " your account has been blocked for security reasons.";
			}

			if (!user.isAuthorizationStatus()) {
				flag = false;
				message = "Dear Mr/Ms." + user.getFname() + " your account has not been activated yet";
			}

			if (!hashedPassword.equals(user.getPassword())) {
				flag = false;
				message = "Username or password is incorrect";
			}
		} catch (Exception var9) {
			flag = false;
			message = "Username or password is incorrect";
		}

		response.setLoginStatus(flag);
		response.setResponseMessage(message);

		try {
			response.setUsername(user.getUsername());
		} catch (Exception var8) {
			response.setUsername(login.getUsername());
		}
		return response;
	}
}
