package com.softwared.banco.util.excepciones;

import java.util.Date;

public class BodyResponse {

	private String message;
	private Date dateException;
	private String statusCode;
	private String severity;

	public BodyResponse(String message, Date dateException, String statusCode, String severity) {
		super();
		this.message = message;
		this.dateException = dateException;
		this.statusCode = statusCode;
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateException() {
		return dateException;
	}

	public void setDateException(Date dateException) {
		this.dateException = dateException;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

}
