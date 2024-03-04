package com.softwared.banco.service;

import org.springframework.stereotype.Service;

import com.softwared.banco.dto.LoginRequest;
import com.softwared.banco.dto.RegisterRequestDto;
import com.softwared.banco.jwt.AuthResponse;

@Service
public interface IAccountService {

	public AuthResponse createAccount(RegisterRequestDto request);

	public AuthResponse loginUser(LoginRequest request);

}
