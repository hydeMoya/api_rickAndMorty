package com.example.rickandmorty.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.rickandmorty.security.JwtAuthenticationEntryPoint;
import com.example.rickandmorty.security.JwtAuthenticationProvider;
import com.example.rickandmorty.security.JwtAuthenticationTokenFilter;
import com.example.rickandmorty.security.JwtSuccessHandler;


@EnableGlobalMethodSecurity (prePostEnabled = true)
@EnableWebSecurity
@Component
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		
		 JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		 filter.setAuthenticationManager(authenticationManager());
		 filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		 
		 return filter;
	}
	
	
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable().authorizeRequests().antMatchers("**/api/**")
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()		
		.headers().frameOptions().disable();
		
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.headers().cacheControl();
	}
}
