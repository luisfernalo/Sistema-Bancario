package com.softwared.banco.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.dto.LoginRequest;
import com.softwared.banco.dto.RegisterRequestDto;
import com.softwared.banco.jwt.AuthResponse;
import com.softwared.banco.service.IAccountService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	
	private final IAccountService iCuentaService;
	
	public AuthenticationController(IAccountService iCuentaService) {
		super();
		this.iCuentaService = iCuentaService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(iCuentaService.loginUser(request));
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto request) {
		return ResponseEntity.ok(iCuentaService.createAccount(request));
	}
}
