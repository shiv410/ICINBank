package com.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.Saccount;
import com.icin.repository.SaccountRepository;
import com.icin.repository.UserRepository;

@Service
public class SaccountCreationService {

	@Autowired
	private SaccountRepository saccountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode="2";
	
	public long generate_saving(int userId) {
		String accNo = bankCode+countryCode+branchCode+accountcode+String.valueOf(userId);
		return Long.parseLong(accNo);
	}

	public Saccount newAccount(String username, int userId) {
		Saccount account =new Saccount();
		account.setUsername(username);
		account.setAccno(generate_saving(userId));
		account.setUser(userRepository.findByUsername(username));
		return saccountRepository.save(account);
	}
	
}
