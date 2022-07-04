package com.example.rickandmorty.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserTO {

	private String username;
	private long id;
	private String role;
	
	
	
}
