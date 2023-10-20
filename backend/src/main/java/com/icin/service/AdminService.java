package com.icin.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.ChequebookRequest;
import com.icin.entity.Transfer;
import com.icin.entity.User;
import com.icin.entity.UserDisplay;
import com.icin.repository.AccountRepository;
import com.icin.repository.ChequeBookRequestsRepository;
import com.icin.repository.SaccountRepository;
import com.icin.repository.TransferRepository;
import com.icin.repository.UserDisplayRepository;
import com.icin.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private ChequeBookRequestsRepository chequeBookRequestsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDisplayRepository userDisplayRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private SaccountRepository aSaccountRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private AccountsService accService;

	@Autowired
	private SaccountCreationService sAccService;

	public void authorizeUser(String username) {
		userRepository.authorizeUser(username);
		System.out.println("error here top");
		User currUser = userRepository.findByUsername(username);
		int userId = currUser.getId();
		System.out.println("error here 1");
		accService.newAccount(username, userId);
		System.out.println("error here 2");
		sAccService.newAccount(username, userId);
	}

	public void cancelAuthorization(String username) {
		userRepository.cancelAuthorization(username);
	}

	public void acceptChequebookRequest(long accNo) {
		String username = "";
		chequeBookRequestsRepository.setChequebookInfoByAccount(accNo);
		if (Long.toString(accNo).length() == 7) {
			username = accountRepository.findByAccno(accNo).getUsername();
		} else {
			username = aSaccountRepository.findByAccno(accNo).getUsername();
		}
		try {
			mailService.sendChequebookConfirmedEmail(username);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enableUser(String username) {
		userRepository.enableUser(username);

	}

	public void disableUser(String username) {
		userRepository.disableUser(username);

	}

	public List<UserDisplay> getAllUsers() {
		return userDisplayRepository.getAllUsers();
	}

	public List<Transfer> getAllTransactions(long accountNo) {
		List<Transfer> sender = transferRepository.findBySaccount(accountNo);
		List<Transfer> receiver = transferRepository.findByRaccount(accountNo);
		List<Transfer> merged = new ArrayList<>();
		merged.addAll(sender);
		merged.addAll(receiver);
		Collections.sort(merged);
		return merged;
	}

	public List<ChequebookRequest> getAllChequebookRequests() {
		return chequeBookRequestsRepository.findAllChequebookRequests();
	}

	
	public List<User> getAllUnauthorizedUsers() {
		return userRepository.findAllUnauthorizedAccounts();
	}

	
	public void setUserFeatures(String username, int featureId) {
		userRepository.setUserFeatureStatus(username, featureId);

	}

	static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;
		return true;
	}

	
	public UserDisplay searchUser(String userDetail) {
		if (isNumber(userDetail)) {
			return userDisplayRepository.getUserDetailsByAccountNo(Long.parseLong(userDetail));
		} else {
			return userDisplayRepository.getUserDetailsByUsername(userDetail);
		}

	}

}
