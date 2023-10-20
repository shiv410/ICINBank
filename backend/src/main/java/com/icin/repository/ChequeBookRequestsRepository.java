package com.icin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icin.entity.ChequebookRequest;

import jakarta.transaction.Transactional;

@Repository
public interface ChequeBookRequestsRepository extends CrudRepository<ChequebookRequest, Integer>{

	@Modifying
	@Transactional
//	@Query("update ChequebookRequest c set c.requestStatus=1 where c.account = ?1")
	@Query("update ChequebookRequest c set c.requestStatus=true where c.account = ?1")
	void setChequebookInfoByAccount(long accNo);
	
	@Query("FROM ChequebookRequest c where c.requestStatus=FALSE")
	public List<ChequebookRequest> findAllChequebookRequests();
	
	
	public List<ChequebookRequest> findByAccount(long account);

}
