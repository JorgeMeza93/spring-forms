package com.cursodespring.springboot.form.app.models.domain;

import javax.validation.constraints.NotEmpty;

public class Usuario {
	@NotEmpty
	private String username;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
