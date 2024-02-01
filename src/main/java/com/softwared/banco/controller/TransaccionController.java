package com.softwared.banco.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Transaccion;
import com.softwared.banco.service.TransaccionService;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

@RestController
@RequestMapping("api/transaccion")
public class TransaccionController {

	@Autowired
	private TransaccionService transaccionService;

	@PutMapping("/depositoDinero")
	public Object depositoDinero(@RequestBody Map<String, String> data) throws SistemaBancarioExcepcion {
		Long numeroCuenta = Long.parseLong(data.get("numeroCuenta"));
		BigDecimal valor = new BigDecimal(data.get("valor"));
		return transaccionService.depositoTransaccion(numeroCuenta, valor);
	}

	@PutMapping("/retiroDinero")
	public Transaccion retiroDinero(@RequestParam Long numeroCuenta, @RequestParam BigDecimal valor)
			throws SistemaBancarioExcepcion, NotFoundException {

		return transaccionService.retiroDinero(numeroCuenta, valor);
	}

	@GetMapping("/obtenerSaldo")
	public BigDecimal obtenerSaldo(@RequestParam Long numeroCuenta) throws Exception {

		return transaccionService.saldoCuenta(numeroCuenta);
	}

	@GetMapping("/obtenerHistorial")
	public List<Transaccion> obtenerHistorialTransacciones(@RequestParam Long numeroCuenta)
			throws SistemaBancarioExcepcion {
		return transaccionService.obtenerHistorialTransacciones(numeroCuenta);
	}
}
