package com.softwared.banco.modelo;

public class LoginRequest {

	private String username;
	private String password;

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

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	public LoginRequest() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String username;
		private String password;

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public LoginRequest build() {
			// Validation can be added here
			if (username == null || username.isEmpty()) {
				throw new IllegalArgumentException("Username cannot be null or empty");
			}
			if (password == null || password.isEmpty()) {
				throw new IllegalArgumentException("Password cannot be null or empty");
			}
			return new LoginRequest(username, password);
		}
	}

}
