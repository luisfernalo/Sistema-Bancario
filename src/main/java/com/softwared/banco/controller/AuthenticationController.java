package com.softwared.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.LoginResponse;
import com.softwared.banco.service.CuentaService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private CuentaService cuentaService;

	@PostMapping("/login")
	public LoginResponse loginUser(@RequestParam String username, @RequestParam String password) {
		return cuentaService.loginResponse(username, password);
	}
}
