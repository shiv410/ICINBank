package com.icin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icin.entity.Saccount;

@Repository
public interface SaccountRepository extends CrudRepository<Saccount, Integer>{
	
	public Saccount findByAccno(long accNo);
	
	public Saccount findByUsername(String username);

}
