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
	
	// 아이디 중복체크 : 타입이 형변환 되기 때문에 String으로 줄 필요없이 바로 Boolean으로 리턴
	@PostMapping("/doubleid")
	public ResponseEntity<Boolean> doubleId(@RequestParam("id") String id){ 
		ResponseEntity<Boolean> res = null;
		System.out.println(id);
		//중복일 때 true, 중복이 아닐때(존재하지 않을때) false => 문자열
		try {
			Boolean isDouble = accountService.checkDoubleId(id);
			res = new ResponseEntity<Boolean> (isDouble, HttpStatus.OK);
		} catch(Exception e) {
			res = new ResponseEntity<Boolean> (false, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	// 입금
	@PostMapping("/deposit")
	public ResponseEntity<Integer> deposit(@RequestParam("id") String id, @RequestParam("money") Integer money){ 
		ResponseEntity<Integer> res = null;
		System.out.println(id);
		try {
			Integer balance = accountService.deposit(id, money);
			res = new ResponseEntity<Integer> (balance, HttpStatus.OK);
		} catch(Exception e) {
			res = new ResponseEntity<Integer> (-1, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	// 출금
	@PostMapping("/withdraw")
	public ResponseEntity<Integer> withdraw(@RequestParam("id") String id, @RequestParam("money") Integer money){ 
		ResponseEntity<Integer> res = null;
		System.out.println(id);
		try {
			Integer balance = accountService.withdraw(id, money);
			res = new ResponseEntity<Integer> (balance, HttpStatus.OK);
		} catch(Exception e) {
			res = new ResponseEntity<Integer> (-1, HttpStatus.BAD_REQUEST);
		} 
		return res;
	}
	
	// 계좌이체
	@PostMapping("/transfer")
	public ResponseEntity<Integer> transfer(@RequestParam("id_s") String id_s, @RequestParam("id_r") String id_r, @RequestParam("money") Integer money){ 
		ResponseEntity<Integer> res = null;
		System.out.println(id_s);
		try {
			Integer balance = accountService.transfer(id_s, id_r, money);
			res = new ResponseEntity<Integer> (balance, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res = new ResponseEntity<Integer> (-1, HttpStatus.BAD_REQUEST);
		} 
		return res;
	}
	
	
}
