package com.example.rickandmorty.security;

import org.springframework.stereotype.Component;

import com.example.rickandmorty.constants.Constants;
import com.example.rickandmorty.dto.JwtUserTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	public JwtUserTO validate(String token) {
		JwtUserTO jwtUser = null;
		
		try {
			
			Claims body = Jwts.parser().setSigningKey(Constants.YOUR_SECRET)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUserTO();
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId(Long.parseLong((String) body.get(Constants.USER_ID)));
			jwtUser.setRole((String) body.get(Constants.ROLE));
			
		}catch(Exception e) {
			
			System.out.println(e);
			
			
		}
		return jwtUser;
		
	}

}
