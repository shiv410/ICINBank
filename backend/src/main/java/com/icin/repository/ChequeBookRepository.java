package com.icin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icin.entity.ChequebookRequest;

@Repository
public interface ChequeBookRepository extends JpaRepository<ChequebookRequest, Integer>{
	
	List<ChequebookRequest> findByAccount(long account);

}
