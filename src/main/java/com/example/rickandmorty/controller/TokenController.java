package com.example.rickandmorty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rickandmorty.dto.JwtTokenResponseTO;
import com.example.rickandmorty.dto.JwtUserTO;
import com.example.rickandmorty.dto.LoginTO;
import com.example.rickandmorty.security.JwtGenerator;





@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {
	
	private  JwtGenerator jwtGenerator;
	
	public TokenController(JwtGenerator jwtGenerator) {
		
		this.jwtGenerator = jwtGenerator;	
	}
	
	@PostMapping()
	public ResponseEntity<JwtTokenResponseTO> generate(@RequestBody final LoginTO login){
		
		JwtUserTO jwtUser = new JwtUserTO();
		
		jwtUser = existUser(login);
		System.out.println("jwtUser : "+ jwtUser);
		
		JwtTokenResponseTO token = new JwtTokenResponseTO();
	
		token.setToken(jwtGenerator.generate(jwtUser));
		
		if(jwtUser != null) {
			
			return new ResponseEntity<JwtTokenResponseTO>(token, HttpStatus.OK);
		}else {
			
			return new ResponseEntity<JwtTokenResponseTO>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	private JwtUserTO existUser(LoginTO login) {
		
		if(login.getUser().equals("test") && login.getPassword().equals("1234")) {
			
			JwtUserTO jwtUser = new JwtUserTO();
			
			jwtUser.setUsername(login.getUser());
			jwtUser.setId(1);
			jwtUser.setRole("Admin");
			
			return jwtUser;
			
		}else {
			return null;
		}
			
	}
	
	

}
