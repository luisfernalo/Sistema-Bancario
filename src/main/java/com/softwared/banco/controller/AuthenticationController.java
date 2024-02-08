package com.softwared.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.jwt.AuthResponse;
import com.softwared.banco.modelo.LoginRequest;
import com.softwared.banco.modelo.RegisterRequest;
import com.softwared.banco.service.CuentaService;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private CuentaService cuentaService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request ) {
		return ResponseEntity.ok(cuentaService.loginUser(request)) ;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request ) throws SistemaBancarioExcepcion {
		return ResponseEntity.ok(cuentaService.createCuenta(request)) ;
	}
}
