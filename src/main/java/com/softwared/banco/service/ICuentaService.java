package com.softwared.banco.service;

import org.springframework.stereotype.Service;

import com.softwared.banco.jwt.AuthResponse;
import com.softwared.banco.modelo.LoginRequest;
import com.softwared.banco.modelo.RegisterRequest;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

@Service
public interface ICuentaService {

	public AuthResponse createCuenta(RegisterRequest request) throws SistemaBancarioExcepcion;

	public AuthResponse loginUser(LoginRequest request);

}
