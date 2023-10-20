package com.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.Account;
import com.icin.entity.Saccount;
import com.icin.entity.User;
import com.icin.repository.AccountRepository;
import com.icin.repository.SaccountRepository;
import com.icin.repository.UserRepository;
import com.icin.response.*;
//import com.icin.response.WithdrawResponse;

@Service
public class SavingsAccountService {

	@Autowired
	private SaccountRepository saccountRepository;

	@Autowired
	private UserHistoryService service;

	@Autowired
	private TransferHistoryService tservice;

	@Autowired
	private AccountRepository adao;

	@Autowired
	private UserRepository udao;

	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode = "2";

	public Saccount getAccount(String username) {
		return this.saccountRepository.findByUsername(username);
	}

	public Saccount getAccountDetails(long account) {
		return this.saccountRepository.findByAccno(account);
	}

	public long generate_saving(int userId) {
		String accNo = "3914918202" + String.valueOf(userId);
		return Long.parseLong(accNo);
	}

	public Saccount newAccount(String username, int userId) {
		Saccount account = new Saccount();
		account.setUsername(username);
		account.setAccno(this.generate_saving(userId));
		account.setUser(this.udao.findByUsername(username));
		return (Saccount) this.saccountRepository.save(account);
	}

	public DepositResponse deposit(long acc, int amount) {
		DepositResponse response = new DepositResponse();
		boolean flag = true;

		try {
			Saccount account = this.saccountRepository.findByAccno(acc);
			account.setBalance(account.getBalance() + amount);
			this.service.addAction(acc, amount, account.getBalance(), "deposit");
			this.saccountRepository.save(account);
			response.setResponseMessage("Rs." + amount + " successfully deposited into your account balance is now Rs."
					+ account.getBalance());
			response.setDepositStatus(flag);
		} catch (Exception var7) {
			flag = false;
			response.setResponseMessage("Account number is incorrect");
			response.setDepositStatus(flag);
		}

		response.setAccount(acc);
		return response;
	}

	public WithdrawResponse withdraw(long acc, int amount) {
		WithdrawResponse response = new WithdrawResponse();
		boolean flag = true;

		try {
			Saccount account = this.saccountRepository.findByAccno(acc);
			User user = this.udao.findByUsername(account.getUsername());
			if (user.getFeatureStatus() != 2 && user.getFeatureStatus() != 3) {
				flag = false;
				response.setResponseMessage("This function is not available for your account");
				response.setWithdrawStatus(flag);
			} else if (account.getBalance() >= amount) {
				account.setBalance(account.getBalance() - amount);
				this.service.addAction(acc, amount, account.getBalance(), "withdraw");
				this.saccountRepository.save(account);
				response.setResponseMessage("Rs." + amount + " successfully withdrawn your account balance is now Rs."
						+ account.getBalance());
				response.setWithdrawStatus(flag);
			} else {
				flag = false;
				response.setResponseMessage("Insufficient funds to complete the transaction");
				response.setWithdrawStatus(flag);
			}
		} catch (Exception var8) {
			flag = false;
			response.setResponseMessage("Account number is incorrect");
			response.setWithdrawStatus(flag);
		}

		response.setAccount(acc);
		return response;
	}

	public TransferResponse transfer(long saccount, long raccount, int amount) {
		TransferResponse response = new TransferResponse();
		boolean flag = true;

		try {
			Saccount senderAccount = this.saccountRepository.findByAccno(saccount);
			User user;
			if (isprimary(raccount)) {
				Account receiverAccount = this.adao.findByAccno(raccount);
				if (senderAccount.getAccno() != receiverAccount.getAccno()) {
					if (senderAccount.getBalance() >= amount) {
						user = this.udao.findByUsername(senderAccount.getUsername());
						if (user.getFeatureStatus() == 3) {
							senderAccount.setBalance(senderAccount.getBalance() - amount);
							receiverAccount.setBalance(receiverAccount.getBalance() + amount);
							this.tservice.addAction(saccount, raccount, amount);
							this.saccountRepository.save(senderAccount);
							this.adao.save(receiverAccount);
							response.setResponseMessage("Rs." + amount + " successfully transferred to account "
									+ receiverAccount.getAccno());
							response.setTransferStatus(flag);
						} else {
							flag = false;
							response.setResponseMessage("This feature is not available for your account");
							response.setTransferStatus(flag);
						}
					} else {
						flag = false;
						response.setResponseMessage("Insufficient funds to complete the transfer");
						response.setTransferStatus(flag);
					}
				} else {
					flag = false;
					response.setResponseMessage("sender and recieiver accounts are same");
					response.setTransferStatus(flag);
				}
			} else {
				Saccount receiverAccount = this.saccountRepository.findByAccno(raccount);
				if (senderAccount.getAccno() != receiverAccount.getAccno()) {
					if (senderAccount.getBalance() > amount) {
						user = this.udao.findByUsername(senderAccount.getUsername());
						if (user.getFeatureStatus() == 3) {
							senderAccount.setBalance(senderAccount.getBalance() - amount);
							receiverAccount.setBalance(receiverAccount.getBalance() + amount);
							this.tservice.addAction(saccount, raccount, amount);
							this.saccountRepository.save(senderAccount);
							this.saccountRepository.save(receiverAccount);
							response.setResponseMessage("Rs." + amount + " successfully transferred to account "
									+ receiverAccount.getAccno());
							response.setTransferStatus(flag);
						} else {
							flag = false;
							response.setResponseMessage("This function isnt available for the account");
							response.setTransferStatus(flag);
						}
					} else {
						flag = false;
						response.setResponseMessage("Insufficient funds to complete the transfer");
						response.setTransferStatus(flag);
					}
				} else {
					flag = false;
					response.setResponseMessage("sender and recieiver accounts are same");
					response.setTransferStatus(flag);
				}
			}
		} catch (Exception var11) {
			flag = false;
			response.setResponseMessage("Account number is incorrect");
			response.setTransferStatus(flag);
		}

		response.setSaccount(saccount);
		return response;
	}

	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check = "3914918201";
		return s.equals(check);
	}

}
