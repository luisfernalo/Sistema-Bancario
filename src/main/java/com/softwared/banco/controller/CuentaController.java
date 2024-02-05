package com.softwared.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.service.CuentaService;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

@RestController
@RequestMapping("api/cuenta")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@PostMapping(path = "/crearCuenta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cuenta crearCuenta(@RequestBody Cuenta cuenta) throws SistemaBancarioExcepcion {
		return cuentaService.createCuenta(cuenta.getNumeroCuenta(), cuenta.getNombreTitular(),
				cuenta.getPasswordTitular(), cuenta.getSaldoInicial());
	}

}
