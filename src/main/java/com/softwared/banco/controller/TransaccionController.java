package com.softwared.banco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Transaccion;
import com.softwared.banco.service.ITransaccionService;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

@RestController
@RequestMapping("api/transaccion")
public class TransaccionController {

	@Autowired
	private ITransaccionService iTransaccionService;

	@PutMapping("/depositoDinero")
	public Transaccion depositoDinero(@RequestParam Long numberAccound, @RequestParam BigDecimal depositValue)
			throws SistemaBancarioExcepcion {
		return (Transaccion) iTransaccionService.depositoTransaccion(numberAccound, depositValue);
	}

	@PutMapping("/retiroDinero")
	public Transaccion retiroDinero(@RequestParam Long numberAccound, @RequestParam BigDecimal withdrawalValue)
			throws SistemaBancarioExcepcion {

		return (Transaccion) iTransaccionService.retiroDinero(numberAccound, withdrawalValue);
	}

	@GetMapping("/obtenerSaldo")
	public BigDecimal obtenerSaldo(@RequestParam Long numberAccound) throws Exception {

		return iTransaccionService.saldoCuenta(numberAccound);
	}

	@GetMapping("/obtenerHistorial")
	public List<Transaccion> obtenerHistorialTransacciones(@RequestParam Long numberAccound)
			throws SistemaBancarioExcepcion {
		return iTransaccionService.obtenerHistorialTransacciones(numberAccound);
	}
}
