package com.softwared.banco.util.excepciones;

public class SistemaBancarioExcepcionDetails {

	private String userMessage;
	private String severity;

	public SistemaBancarioExcepcionDetails(String userMessage, String severity) {
		super();
		this.userMessage = userMessage;
		this.severity = severity;
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
