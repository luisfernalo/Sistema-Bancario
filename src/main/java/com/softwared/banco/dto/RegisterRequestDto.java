package com.softwared.banco.dto;

import java.math.BigDecimal;

public class RegisterRequestDto {

	private Long numberAccount;
	private String holderName;
	private String holderLastName;
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
	

	public String getHolderLastName() {
		return holderLastName;
	}

	public void setHolderLastName(String holderLastName) {
		this.holderLastName = holderLastName;
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

	public RegisterRequestDto(Long numberAccount, String holderName, String holderLastName,String holderEmail, String holderpassword,
			BigDecimal initialBalance) {
		super();
		this.numberAccount = numberAccount;
		this.holderEmail = holderEmail;
		this.holderName = holderName;
		this.holderLastName= holderLastName;
		this.holderpassword = holderpassword;
		this.initialBalance = initialBalance;
	}

	public RegisterRequestDto() {
		super();
	}

	public static class Builder {

		private Long numberAccount;
		private String holderName;
		private String holderLastName;
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
		public Builder withHolderLastName(String holderLastName) {
			this.holderLastName=holderLastName;
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

		public RegisterRequestDto build() {
			return new RegisterRequestDto(numberAccount, holderName, holderLastName,holderEmail, holderpassword, initialBalance);
		}
	}
}
