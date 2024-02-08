package com.softwared.banco.modelo;

import java.math.BigDecimal;

public class RegisterRequest {

	private Long numeroCuenta;
	private String username;
	private String password;
	private BigDecimal initialBalance;

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public RegisterRequest(Long numeroCuenta, String username, String password, BigDecimal initialBalance) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.username = username;
		this.password = password;
		this.initialBalance = initialBalance;
	}

	public RegisterRequest() {
		super();
	}

	public static class Builder {

		private Long numeroCuenta;
		private String username;
		private String password;
		private BigDecimal initialBalance;

		public Builder withNumeroCuenta(Long numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withInitialBalance(BigDecimal initialBalance) {
			this.initialBalance = initialBalance;
			return this;
		}

		public RegisterRequest build() {
			return new RegisterRequest(numeroCuenta, username, password, initialBalance);
		}
	}
}
