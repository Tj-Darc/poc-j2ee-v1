package com.poc.request;

// La demande d'entrée JSON fournie par l'utilisateur sera annulée en objet Java à l'aide de cette classe.

public class AuthenticationRequest {

	private String username;
	private String password;

	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequest() {

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

}
