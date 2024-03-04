package com.softwared.banco.util.excepciones;

import org.springframework.http.HttpStatus;

public class SistemaBancarioExcepcionDetails extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String userMessage;
	private String severity;
	private HttpStatus code;

	public SistemaBancarioExcepcionDetails() {

	}

	public SistemaBancarioExcepcionDetails(String userMessage, String severity, HttpStatus code) {
		super(userMessage);
		this.userMessage = userMessage;
		this.severity = severity;
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

}
