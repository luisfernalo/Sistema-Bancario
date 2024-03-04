package com.softwared.banco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Transaction;
import com.softwared.banco.service.ITransactionService;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

	@Autowired
	private ITransactionService iTransaccionService;

	@PutMapping("/depositMoney")
	public Transaction depositMoney(@RequestParam Long numberAccound, @RequestParam BigDecimal depositValue) {
		return (Transaction) iTransaccionService.depositTransaction(numberAccound, depositValue);
	}

	@PutMapping("/cashOut")
	public Transaction cashOut(@RequestParam Long numberAccound, @RequestParam BigDecimal withdrawalValue) {

		return (Transaction) iTransaccionService.withdrawal(numberAccound, withdrawalValue);
	}

	@GetMapping("/getBalance")
	public BigDecimal getBalance(@RequestParam Long numberAccound) throws Exception {

		return iTransaccionService.accountBalance(numberAccound);
	}

	@GetMapping("/getTransactionHistory")
	public List<Transaction> getTransactionHistory(@RequestParam Long numberAccound) {
		return iTransaccionService.getTransactionHistory(numberAccound);
	}
}
