package com.softwared.banco;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootTest
class SistemaBancarioApplicationTests {

	@Test
	void contextLoads() {
		 var encoders = new BCryptPasswordEncoder();
		System.out.println(encoders.encode("user1234"));
	}

}
