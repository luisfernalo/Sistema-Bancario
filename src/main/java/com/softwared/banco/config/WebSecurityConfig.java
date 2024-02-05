package com.softwared.banco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(UserDetailsService detailsService) {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(detailsService);
		return new ProviderManager(daoProvider);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authRequets -> {
			authRequets.requestMatchers("/auth/**").permitAll();
			authRequets.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();
	}
	/*
	 * @Bean public JwtDecoder jwtDecoder() { return
	 * NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build(); }
	 * 
	 * @Bean public JwtEncoder jwtEncoder() { JWK jwk = new
	 * RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
	 * JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
	 * return new NimbusJwtEncoder(jwks); }
	 */
}
