package com.icin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.Account;
import com.icin.entity.ChequebookRequest;
import com.icin.entity.Saccount;
import com.icin.repository.AccountRepository;
import com.icin.repository.ChequeBookRepository;
import com.icin.repository.SaccountRepository;
import com.icin.response.ChequeResponse;

@Service
public class ChequebookService {

	@Autowired
	private ChequeBookRepository dao;
	
	@Autowired
	private AccountRepository aRepository ;
	
	@Autowired
	private SaccountRepository sRepository;


	public ChequeResponse createrequest(ChequebookRequest chequebook) {
		ChequeResponse response = new ChequeResponse();
		long account = chequebook.getAccount();
		List<ChequebookRequest> prevRequests = this.dao.findByAccount(account);
		if (!prevRequests.isEmpty()) {
			for (int i = 0; i < prevRequests.size(); ++i) {
				if (!((ChequebookRequest) prevRequests.get(i)).isRequestStatus()) {
					response.setResponseMessage("Your previous chequebook request is still pending.");
					response.setStatus(false);
					response.setAccount(account);
					return response;
				}
			}
		}

		LocalDate today = LocalDate.now();
		if (isprimary(account)) {
			try {
				Account account1 = this.aRepository.findByAccno(account);
				response.setAccount(account1.getAccno());
				response.setStatus(true);
				response.setResponseMessage("Request submitted successfully");
				chequebook.setAccType("Primary");
				chequebook.setDate(today);
				chequebook.setRequestStatus(false);
				this.dao.save(chequebook);
			} catch (Exception var9) {
				response.setAccount(account);
				response.setStatus(false);
				response.setResponseMessage("account number is incorrect");
			}
		} else if (isSecondary(account)) {
			try {
				Saccount saccount = this.sRepository.findByAccno(account);
				response.setAccount(saccount.getAccno());
				response.setStatus(true);
				response.setResponseMessage("Request submitted successfully");
				chequebook.setRequestStatus(false);
				chequebook.setAccType("Secondary");
				chequebook.setDate(today);
				this.dao.save(chequebook);
			} catch (Exception var8) {
				response.setAccount(account);
				response.setStatus(false);
				response.setResponseMessage("account number is incorrect");
			}
		} else {
			response.setAccount(account);
			response.setStatus(false);
			response.setResponseMessage("account number is incorrect");
		}

		return response;
	}

	public List<ChequebookRequest> getRequests(long account) {
		return this.dao.findByAccount(account);
	}

	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check = "3914918201";
		return s.equals(check);
	}

	public static boolean isSecondary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check = "3914918202";
		return s.equals(check);
	}

}
