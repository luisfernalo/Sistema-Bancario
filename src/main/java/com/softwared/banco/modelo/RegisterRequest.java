package com.softwared.banco.modelo;

import java.math.BigDecimal;

public class RegisterRequest {

	private Long numberAccount;
	private String holderName;
	private String holderEmail;
	private String holderpassword;
	private BigDecimal initialBalance;

	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getHolderEmail() {
		return holderEmail;
	}

	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderpassword() {
		return holderpassword;
	}

	public void setHolderpassword(String holderpassword) {
		this.holderpassword = holderpassword;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public RegisterRequest(Long numberAccount, String holderName, String holderEmail, String holderpassword,
			BigDecimal initialBalance) {
		super();
		this.numberAccount = numberAccount;
		this.holderEmail = holderEmail;
		this.holderName = holderName;
		this.holderpassword = holderpassword;
		this.initialBalance = initialBalance;
	}

	public RegisterRequest() {
		super();
	}

	public static class Builder {

		private Long numberAccount;
		private String holderName;
		private String holderpassword;
		private String holderEmail;
		private BigDecimal initialBalance;

		public Builder withNumeroCuenta(Long numberAccount) {
			this.numberAccount = numberAccount;
			return this;
		}

		public Builder withHolderName(String holderName) {
			this.holderName = holderName;
			return this;
		}

		public Builder withHolderEmail(String holderEmail) {
			this.holderEmail = holderEmail;
			return this;
		}

		public Builder withHolderpassword(String holderpassword) {
			this.holderpassword = holderpassword;
			return this;
		}

		public Builder withInitialBalance(BigDecimal initialBalance) {
			this.initialBalance = initialBalance;
			return this;
		}

		public RegisterRequest build() {
			return new RegisterRequest(numberAccount, holderName, holderEmail, holderpassword, initialBalance);
		}
	}
}
