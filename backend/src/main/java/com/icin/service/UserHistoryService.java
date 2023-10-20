package com.icin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.UserHistory;
import com.icin.repository.UserHistoryRepository;

@Service
public class UserHistoryService {

	@Autowired
	private UserHistoryRepository dao;

	
	public UserHistory addAction(long account, int amount, int balance, String action) {
		LocalDate today = LocalDate.now();
		UserHistory row = new UserHistory();
		row.setAccount(account);
		row.setAction(action);
		row.setAmount(amount);
		row.setDate(today);
		return (UserHistory) this.dao.save(row);
	}

	public List<UserHistory> getHistory(long account) {
		return this.dao.findByAccount(account);
	}

}
