package com.softwared.banco.modelo;

public class LoginResponse {

	private Cuenta cuenta;
	private String jwt;

	public LoginResponse() {
		super();
	}

	public LoginResponse(Cuenta cuenta, String jwt) {
		super();
		this.cuenta = cuenta;
		this.jwt = jwt;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getJwt() {
		return this.jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
