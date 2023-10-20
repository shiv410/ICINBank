package com.icin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icin.entity.ChequebookRequest;
import com.icin.response.ChequeResponse;
import com.icin.service.ChequebookService;

@RestController
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
public class ChequeBookController {

	@Autowired
	private ChequebookService service;

	public ChequeBookController() {
	}

	@PostMapping({ "/cheque/request" })
	public ChequeResponse createrequest(@RequestBody ChequebookRequest chequebook) {
		return this.service.createrequest(chequebook);
	}

	@GetMapping({ "/cheque/getbyAccount/{account}" })
	public List<ChequebookRequest> getRequests(@PathVariable("account") long account) {
		List<ChequebookRequest> list = this.service.getRequests(account);
		return list;
	}

}
