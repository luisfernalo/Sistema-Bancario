package com.softwared.banco;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.softwared.banco.modelo.Authority;
import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.repository.AuthorityRepository;
import com.softwared.banco.repository.CuentaRepository;
import com.softwared.banco.util.enums.AuthorityName;

@SpringBootApplication
public class SistemaBancarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaBancarioApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner run (CuentaRepository cuentaRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder){
		return args->{
			
			if(authorityRepository.findByName("USER").isPresent()) return;
			
			
			//Authority adminrole = authorityRepository.save(new Authority(AuthorityName.USER.toString()));
			//authorityRepository.save(new Authority(AuthorityName.USER.toString()));
			//Set<Authority> roles = new HashSet<>();
			//roles.add(adminrole);
			
			//Cuenta admin = new Cuenta(Long.parseLong("123456"), "luis", passwordEncoder.encode("user1234"), BigDecimal.valueOf(10000),roles);
			
			//cuentaRepository.save(admin);
		};
	}*/

}
