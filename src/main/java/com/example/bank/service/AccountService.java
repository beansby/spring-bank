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
	
	// 중복 여부 체크
	public Boolean checkDoubleId(String id) throws Exception {
		Optional<Account> account = accountRepository.findById(id);
		// id가 있으면 true : 이미 존재하는 아이디
		if(account.isPresent()) return true; 
		// 사용 가능한 아이디 
		return false;
	}
	
	// 입금 : 계좌 조회, 잔액 가져오기, balance + money
	public Integer deposit(String id, Integer money) throws Exception{
		Optional<Account> account = accountRepository.findById(id);
		// 계좌번호 없으면 오류 출력
		if(!account.isPresent()) throw new Exception("계좌번호 오류");
		Account acc = account.get();
		acc.deposit(money);
		accountRepository.save(acc);
		return acc.getBalance();
	}

	// 출금 : 계좌 조회, 잔액 가져오기, balance - money
	public Integer withdraw(String id, Integer money) throws Exception{
		Optional<Account> account = accountRepository.findById(id);
		// 계좌번호 없으면 오류 출력
		if(!account.isPresent()) throw new Exception("계좌번호 오류");
		Account acc = account.get();
		acc.withdraw(money);
		accountRepository.save(acc);
		return acc.getBalance();
	}
	
	
//	// 계좌 조회 (목록)
//	public List<Account> findAccount(){ return accountRepository.findAll();}
//	// 계좌 조회 (id => 한건)
//	public Optional<Account> findOne(String id) { return accountRepository.findById(id);}
}
