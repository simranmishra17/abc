package com.lms.lmsdemo.model;

public class JWTResponse {

	private String token;

	public JWTResponse() {
	}

	public JWTResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
