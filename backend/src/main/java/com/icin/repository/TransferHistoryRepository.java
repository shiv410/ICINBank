package com.icin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icin.entity.Transfer;

@Repository
public interface TransferHistoryRepository extends JpaRepository<Transfer, Integer> {

	List<Transfer> findBySaccount(long saccount);

	List<Transfer> findByRaccount(long raccount);
	
}
