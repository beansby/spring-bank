package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;

// 실제 뷰를 주지 않고 데이터를 내려보내줌(ResponseEntity를 통해)
@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(Account acc){
		ResponseEntity<String> res = null;
		System.out.println(acc);
		// 예외처리
		try {
			accountService.makeAccount(acc);
			res = new ResponseEntity<String> ("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res = new ResponseEntity<String> ("error", HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}
}
