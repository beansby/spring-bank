package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public void makeAccount(Account acc) throws Exception {
		acc.setBalance(0); //초기화
		accountRepository.save(acc);
	}
}
