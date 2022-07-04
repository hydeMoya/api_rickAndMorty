package com.example.rickandmorty.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.rickandmorty.config.JwtAuthenticationToken;
import com.example.rickandmorty.constants.Constants;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationTokenFilter() {
		super("/api/**");// los ** significan que desde /api hacia dentro lee todo.
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
		
		//System.out.println("Header ::"+ header);
		if(header == null || !header.startsWith(Constants.BEARER_TOKEN)) {
			
			throw new RuntimeException("Jwt es incorrecto no ha llegado nada");
		}	
		
		String authenticationToken = header.substring(7);
		
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
	
	
	
	

}
