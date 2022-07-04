package com.example.rickandmorty.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{
	
	private static final long serialVersionUID = 1L;
	private String token;
	
	public JwtAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}
	//Override Implemets Metodos 
	@Override
	public Object getCredentials() {
		
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
