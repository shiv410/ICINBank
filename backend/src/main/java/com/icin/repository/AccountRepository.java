package com.icin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icin.entity.Account;

@Repository
public interface AccountRepository  extends CrudRepository<Account, Integer>{

	public Account findByUsername(String username);
	public Account findByAccno(long accno);
	
}
