package com.softwared.banco.service;

import java.math.BigDecimal;
import java.util.List;

import com.softwared.banco.modelo.Transaccion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;

public interface ITransaccionService {

	public Transaccion depositoTransaccion(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion;

	public BigDecimal saldoCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion;

	public Transaccion retiroDinero(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion;

	public List<Transaccion> obtenerHistorialTransacciones(Long numeroCuenta) throws SistemaBancarioExcepcion;
}
