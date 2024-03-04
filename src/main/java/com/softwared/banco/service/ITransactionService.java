package com.softwared.banco.service;

import java.math.BigDecimal;
import java.util.List;

import com.softwared.banco.modelo.Transaction;

public interface ITransactionService {

	public Transaction depositTransaction(Long numeroCuenta, BigDecimal valor);

	public BigDecimal accountBalance(Long numeroCuenta);

	public Transaction withdrawal(Long numeroCuenta, BigDecimal valor);

	public List<Transaction> getTransactionHistory(Long numeroCuenta);
}
