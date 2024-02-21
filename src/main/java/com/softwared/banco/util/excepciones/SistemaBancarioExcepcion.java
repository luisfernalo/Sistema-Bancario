package com.softwared.banco.util.excepciones;

public class SistemaBancarioExcepcion extends Exception {

	private static final long serialVersionUID = 6066041750260621877L;

	private SistemaBancarioExcepcionDetails details;

	public SistemaBancarioExcepcion(String message, SistemaBancarioExcepcionDetails details, Throwable e) {
		super(message, e);
		setDetails(details);
	}

	public SistemaBancarioExcepcion(String message, SistemaBancarioExcepcionDetails details) {
		super(message);
		setDetails(details);
	}

	public SistemaBancarioExcepcionDetails getDetails() {
		return details;
	}

	public void setDetails(SistemaBancarioExcepcionDetails details) {
		this.details = details;
	}

}
