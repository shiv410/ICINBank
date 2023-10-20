package com.icin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.entity.Transfer;
import com.icin.repository.TransferHistoryRepository;

@Service
public class TransferHistoryService {

	@Autowired
	private TransferHistoryRepository tHistoryRepository;

	

	public Transfer addAction(long saccount, long raccount, int amount) {
		LocalDate today = LocalDate.now();
		Transfer transfer = new Transfer();
		transfer.setSaccount(saccount);
		transfer.setRaccount(raccount);
		transfer.setAmount(amount);
		transfer.setDate(today);
		return (Transfer) this.tHistoryRepository.save(transfer);
	}

	public List<Transfer> getTransfers(long account) {
		List<Transfer> sender = this.tHistoryRepository.findBySaccount(account);
		List<Transfer> receiver = this.tHistoryRepository.findByRaccount(account);
		List<Transfer> merged = new ArrayList<Transfer>();
		merged.addAll(sender);
		merged.addAll(receiver);
		Collections.sort(merged);
		return merged;
	}
}
