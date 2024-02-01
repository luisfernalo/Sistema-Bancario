package com.softwared.banco.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.softwared.banco.modelo.Authority;
import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.repository.AuthorityRepository;
import com.softwared.banco.repository.CuentaRepository;
import com.softwared.banco.util.enums.AuthorityName;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;

@Component
public class CuentaService {
	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public Cuenta createCuenta(Long numeroCuenta, String nombreTitular, String passwordTitular,
			BigDecimal saldoInicial) throws SistemaBancarioExcepcion {

		
		if (numeroCuenta.toString().length() <= 4) {
			throw new SistemaBancarioExcepcion("numero de cuenta corto",
					new SistemaBancarioExcepcionDetails("Numero de cuenta debe ser mayor de 4 digitos", "error"));
		}
		validarNumeroCuenta(numeroCuenta);
		
		 
		Authority roleuser = authorityRepository.findByName(AuthorityName.USER.toString()).get();
		Set<Authority> rol = new HashSet<>();
		rol.add(roleuser);

		return cuentaRepository.save(new Cuenta(numeroCuenta, nombreTitular, passwordEncoder.encode(passwordTitular),saldoInicial,rol));

	}

	public void validarNumeroCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
		if (cuenta != null) {
			throw new SistemaBancarioExcepcion("numero de cuenta ya existe",
					new SistemaBancarioExcepcionDetails("Numero de cuenta no se puede usar", "error"));
		}
	}

	public String createCuenta() {
		// TODO Auto-generated method stub
		return null;
	}

}
