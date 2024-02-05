package com.softwared.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
