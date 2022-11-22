package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;

// 실제 뷰를 주지 않고 데이터를 내려보내줌(ResponseEntity를 통해) : 리턴 타입이 뷰가 아닌 데이터
@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;

	// 계좌 생성 
	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(Account acc){
		ResponseEntity<String> res = null;
		System.out.println(acc);
		// 예외처리 : generic(실제 리턴되는 데이터), httpstatus
		try {
			accountService.makeAccount(acc);
			res = new ResponseEntity<String> ("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res = new ResponseEntity<String> ("error", HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}
	
	// 계좌 조회
	@PostMapping("/accountinfo")
	public ResponseEntity<Account> accountinfo(@RequestParam("id") String id){
		ResponseEntity<Account> res = null;
		System.out.println("id:"+id);
		try {
			Account acc = accountService.accountinfo(id);
			res = new ResponseEntity<Account> (acc, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res = new ResponseEntity<Account> (new Account(), HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
}
