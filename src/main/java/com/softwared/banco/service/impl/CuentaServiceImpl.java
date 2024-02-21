package com.softwared.banco.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwared.banco.jwt.AuthResponse;
import com.softwared.banco.jwt.JwtService;
import com.softwared.banco.modelo.Authority;
import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.modelo.LoginRequest;
import com.softwared.banco.modelo.RegisterRequest;
import com.softwared.banco.repository.AuthorityRepository;
import com.softwared.banco.repository.CuentaRepository;
import com.softwared.banco.service.ICuentaService;
import com.softwared.banco.util.enums.AuthorityName;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;

@Service
public class CuentaServiceImpl implements ICuentaService{
	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtService jwtService;

	@Override
	public AuthResponse createCuenta(RegisterRequest request) throws SistemaBancarioExcepcion {

		validarNumeroCuenta(request.getNumberAccount());

		Authority roleuser = authorityRepository.findByName(AuthorityName.USER.toString()).get();
		Set<Authority> rol = new HashSet<>();
		rol.add(roleuser);

		Cuenta cuenta = cuentaRepository.save(new Cuenta(request.getNumberAccount(), request.getHolderName(),request.getHolderEmail(),
				passwordEncoder.encode(request.getHolderpassword()), request.getInitialBalance(), rol));

		var jwtToken = jwtService.generateToken(cuenta);

		return AuthResponse.builder().token(jwtToken).build();
	}

	private void validarNumeroCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {
		if (numeroCuenta.toString().length() <= 4) {
			throw new SistemaBancarioExcepcion("numero de cuenta corto",
					new SistemaBancarioExcepcionDetails("Numero de cuenta debe ser mayor de 4 digitos", "error"));
		}
		Cuenta cuenta = cuentaRepository.findByNumberAccount(numeroCuenta);
		if (cuenta != null) {
			throw new SistemaBancarioExcepcion("numero de cuenta ya existe",
					new SistemaBancarioExcepcionDetails("Numero de cuenta no se puede usar", "error"));
		}
	}

	@Override
	public AuthResponse loginUser(LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var cuenta = cuentaRepository.findByHolderEmail(request.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(cuenta);

		return AuthResponse.builder().token(jwtToken).build();
	}
}