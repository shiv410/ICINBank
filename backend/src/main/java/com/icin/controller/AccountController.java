package com.icin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icin.details.TransactionDetails;
import com.icin.details.TransferDetails;
import com.icin.entity.Account;
import com.icin.entity.Saccount;
import com.icin.entity.Transfer;
import com.icin.entity.UserHistory;
import com.icin.repository.AccountRepository;
import com.icin.repository.SaccountRepository;
import com.icin.response.DepositResponse;
import com.icin.response.TransferResponse;
import com.icin.response.WithdrawResponse;
import com.icin.service.AccountsService;
import com.icin.service.SavingsAccountService;
import com.icin.service.TransferHistoryService;
import com.icin.service.UserHistoryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

	@Autowired
	private AccountsService aservice;

	@Autowired
	private SavingsAccountService sservice;

	@Autowired
	private UserHistoryService hservice;

	@Autowired
	private TransferHistoryService tservice;

	@Autowired
	private AccountRepository aRepository;

	@Autowired
	private SaccountRepository sRepository;

	private final String ifsc = "ICIN7465";

	public AccountController() {
	}

	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check = "3914918201";
		return s.equals(check);
	}

	@GetMapping({ "/account/details/{account}" })
	public Account getAccountDetails(@PathVariable("account") int account) {
		return this.aservice.getAccountDetails((long) account);
	}

	@PutMapping({ "/account/profile" })
	public Account updateProfile(@RequestBody Account account) {
		return this.aservice.updateAccount(account);
	}

	@GetMapping({ "/account/getprimary/{username}" })
	public Account getPrimarydetails(@PathVariable("username") String username) {
		return this.aservice.getAccount(username);
	}

	@GetMapping({ "/account/getsaving/{username}" })
	public Saccount getSavingdetails(@PathVariable("username") String username) {
		return this.sservice.getAccount(username);
	}

	@PostMapping({ "/account/deposit" })
	public DepositResponse deposit(@RequestBody TransactionDetails details) {
		return isprimary(details.getAccount()) ? this.aservice.deposit(details.getAccount(), details.getAmount())
				: this.sservice.deposit(details.getAccount(), details.getAmount());
	}

	@PostMapping({ "/account/withdraw" })
	public WithdrawResponse withdraw(@RequestBody TransactionDetails details) {
		return isprimary(details.getAccount()) ? this.aservice.withdraw(details.getAccount(), details.getAmount())
				: this.sservice.withdraw(details.getAccount(), details.getAmount());
	}

	@PostMapping({ "/account/transfer" })
	public TransferResponse transfer(@RequestBody TransferDetails details) {
		try {
			if (details.getIfsc().equals("ICIN7465")) {
				Account p = this.aRepository.findByUsername(details.getUsername());
				Saccount s = this.sRepository.findByUsername(details.getUsername());
				if (p.getAccno() != details.getSaccount() && s.getAccno() != details.getSaccount()) {
					TransferResponse response = new TransferResponse();
					response.setSaccount(details.getSaccount());
					response.setResponseMessage(
							"Dear user You can only transfer funds from the accounts registed with you");
					response.setTransferStatus(false);
					return response;
				} else {
					return isprimary(details.getSaccount())
							? this.aservice.transfer(details.getSaccount(), details.getRaccount(), details.getAmount())
							: this.sservice.transfer(details.getSaccount(), details.getRaccount(), details.getAmount());
				}
			} else {
				TransferResponse response = new TransferResponse();
				response.setSaccount(details.getSaccount());
				response.setResponseMessage("IFSC code is incorrect");
				response.setTransferStatus(false);
				return response;
			}
		} catch (Exception var5) {
			TransferResponse response = new TransferResponse();
			response.setSaccount(details.getSaccount());
			response.setResponseMessage("Please provide an IFSC code");
			response.setTransferStatus(false);
			return response;
		}
	}

	@GetMapping({ "/account/getHistory/{account}" })
	public List<UserHistory> getHistory(@PathVariable("account") long account) {
		List<UserHistory> history = this.hservice.getHistory(account);
		Collections.reverse(history);
		return history;
	}

	@GetMapping({ "/account/getTransfers/{account}" })
	public List<Transfer> getTransfers(@PathVariable("account") long account) {
		return this.tservice.getTransfers(account);
	}

}
