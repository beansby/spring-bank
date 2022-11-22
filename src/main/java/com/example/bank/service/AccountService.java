package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	// 계좌 생성 
	public void makeAccount(Account acc) throws Exception {
		acc.setBalance(0); //초기화
		accountRepository.save(acc);
	}
	
	// 계좌 조회
	public Account accountinfo (String id) throws Exception {
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isPresent()) throw new Exception("계좌번호 오류");
		return account.get();
	}
	
	
//	// 계좌 조회 (목록)
//	public List<Account> findAccount(){ return accountRepository.findAll();}
//	// 계좌 조회 (id => 한건)
//	public Optional<Account> findOne(String id) { return accountRepository.findById(id);}
}
