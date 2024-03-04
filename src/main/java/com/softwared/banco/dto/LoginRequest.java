package com.softwared.banco.dto;

public class LoginRequest {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

	public LoginRequest() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String email;
		private String password;

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public LoginRequest build() {
			
			if (email == null || email.isEmpty()) {
				throw new IllegalArgumentException("Username cannot be null or empty");
			}
			if (password == null || password.isEmpty()) {
				throw new IllegalArgumentException("Password cannot be null or empty");
			}
			return new LoginRequest(email, password);
		}
	}

}
