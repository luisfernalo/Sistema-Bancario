package com.softwared.banco.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.softwared.banco.util.enums.TransactionStatusEnum;
import com.softwared.banco.util.enums.TransactionTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransaction;

	@Column
	private Long numberAccount;

	@Column
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum typeTransaction;
	@Column
	private BigDecimal valueTransaction;
	private LocalDateTime dateTransaction;
	@Column
	@Enumerated(EnumType.STRING)
	private TransactionStatusEnum stateTransaction;

	public Transaction() {
		super();
	}

	public Transaction(Long numberAccount, BigDecimal valueTransaction, TransactionTypeEnum typeTransaction, LocalDateTime localDateTime,
			TransactionStatusEnum stateTransaction) {
		super();

		this.numberAccount = numberAccount;
		this.typeTransaction = typeTransaction;
		this.valueTransaction = valueTransaction;
		this.dateTransaction = localDateTime;
		this.stateTransaction = stateTransaction;
	}

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public TransactionTypeEnum getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(TransactionTypeEnum typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public BigDecimal getValueTransaction() {
		return valueTransaction;
	}

	public void setValueTransaction(BigDecimal valueTransaction) {
		this.valueTransaction = valueTransaction;
	}

	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public TransactionStatusEnum getStateTransaction() {
		return stateTransaction;
	}

	public void setStateTransaction(TransactionStatusEnum stateTransaction) {
		this.stateTransaction = stateTransaction;
	}

}
