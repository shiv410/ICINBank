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

@Service
public class AccountsService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserHistoryService service;

	@Autowired
	private TransferHistoryService tservice;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SaccountRepository sdao;
	
	private final String bankCode = "3914";
	private final String countryCode = "91";
	private final String branchCode = "820";
	private final String accountcode = "1";

	public long generate_saving(int userId) {
		String accNo = "3914918201" + String.valueOf(userId);
		return Long.parseLong(accNo);
	}

	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check = "3914918201";
		return s.equals(check);
	}

	public Account newAccount(String username, int userId) {
		Account account = new Account();
		account.setUsername(username);
		account.setAccno(this.generate_saving(userId));
		account.setUser(this.userRepo.findByUsername(username));
		return (Account) this.accountRepo.save(account);
	}

	public Account getAccount(String username) {
		return this.accountRepo.findByUsername(username);
	}

	public DepositResponse deposit(long acc, int amount) {
		DepositResponse response = new DepositResponse();
		boolean flag = true;

		try {
			Account account = this.accountRepo.findByAccno(acc);
			account.setBalance(account.getBalance() + amount);
			this.service.addAction(acc, amount, account.getBalance(), "credit");
			this.accountRepo.save(account);
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
			Account account = this.accountRepo.findByAccno(acc);
			User user = this.userRepo.findByUsername(account.getUsername());
			if (user.getFeatureStatus() != 2 && user.getFeatureStatus() != 3) {
				flag = false;
				response.setResponseMessage("This function is not available for your account");
				response.setWithdrawStatus(flag);
			} else if (account.getBalance() >= amount) {
				account.setBalance(account.getBalance() - amount);
				this.service.addAction(acc, amount, account.getBalance(), "debit");
				this.accountRepo.save(account);
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
			Account senderAccount = this.accountRepo.findByAccno(saccount);
			User user;
			if (isprimary(raccount)) {
				Account receiverAccount = this.accountRepo.findByAccno(raccount);
				if (senderAccount.getAccno() != receiverAccount.getAccno()) {
					if (senderAccount.getBalance() > amount) {
						user = this.userRepo.findByUsername(senderAccount.getUsername());
						if (user.getFeatureStatus() == 3) {
							senderAccount.setBalance(senderAccount.getBalance() - amount);
							receiverAccount.setBalance(receiverAccount.getBalance() + amount);
							this.tservice.addAction(saccount, raccount, amount);
							this.accountRepo.save(senderAccount);
							this.accountRepo.save(receiverAccount);
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
				Saccount receiverAccount = this.sdao.findByAccno(raccount);
				if (senderAccount.getAccno() != receiverAccount.getAccno()) {
					if (senderAccount.getBalance() > amount) {
						user = this.userRepo.findByUsername(senderAccount.getUsername());
						if (user.getFeatureStatus() == 3) {
							senderAccount.setBalance(senderAccount.getBalance() - amount);
							receiverAccount.setBalance(receiverAccount.getBalance() + amount);
							this.tservice.addAction(saccount, raccount, amount);
							this.accountRepo.save(senderAccount);
							this.sdao.save(receiverAccount);
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

	public Account getAccountDetails(long account) {
		return this.accountRepo.findByAccno(account);
	}

	public Account updateAccount(Account account) {
		return (Account) this.accountRepo.save(account);
	}

}
