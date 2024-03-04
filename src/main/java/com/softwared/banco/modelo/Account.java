package com.softwared.banco.modelo;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account")
	private Integer idAccount;

	@Column(name = "initial_balance")
	private BigDecimal initialBalance;

	@Column(name = "number_account")
	private Long numberAccount;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "person_id")
	private Person person;

	public Account() {
		super();
	}

	public Account(BigDecimal initialBalance, Long numberAccount, Person person) {
		super();
		this.initialBalance = initialBalance;
		this.numberAccount = numberAccount;
		this.person = person;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
