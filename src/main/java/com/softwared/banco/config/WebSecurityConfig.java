package com.softwared.banco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.softwared.banco.jwt.JwtAuthenticationFilter;
import com.softwared.banco.util.enums.AuthorityName;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 

public class WebSecurityConfig {

	@Autowired
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private final AuthenticationProvider authenticationProvider;
	
	public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf-> csrf.disable())
					.authorizeHttpRequests(auth->{ 
							auth.requestMatchers("/api/auth/login").permitAll();
							auth.requestMatchers("/api/auth/register").hasAuthority(AuthorityName.ADMIN.toString());
							auth.anyRequest().authenticated();
							})
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authenticationProvider(authenticationProvider)
					.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	
	
	
}
