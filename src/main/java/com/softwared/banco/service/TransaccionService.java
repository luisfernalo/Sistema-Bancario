package com.softwared.banco.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.modelo.Parametro;
import com.softwared.banco.modelo.Transaccion;
import com.softwared.banco.repository.CuentaRepository;
import com.softwared.banco.repository.TipoParametroRepository;
import com.softwared.banco.repository.TransaccionRepository;
import com.softwared.banco.util.enums.ClaveParametro;
import com.softwared.banco.util.enums.EstadoTransacion;
import com.softwared.banco.util.enums.TipoTransacion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;
import com.softwared.banco.util.message.LocaleResolverConfig;

@Component
public class TransaccionService {

	@Autowired
	private TransaccionRepository transaccionRepository;
	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private TipoParametroRepository tipoParametroRepository;

	LocaleResolverConfig localeResolverConfig = new LocaleResolverConfig();

	// Metodo para obtener Cuenta por el numero de cuenta
	public Cuenta getCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {

		Optional<Cuenta> Cuenta = Optional.ofNullable(cuentaRepository.findByNumberAccount(numeroCuenta));
		Cuenta.orElseThrow(
				() -> new SistemaBancarioExcepcion(
						localeResolverConfig.messageSource().getMessage("not.found.cuenta", null, Locale.getDefault()),
						new SistemaBancarioExcepcionDetails(
								localeResolverConfig.messageSource().getMessage("invalid.cuenta", null, Locale.US),
								"error")));

		return Cuenta.get();
	}

	// Metodo para deposito de dinero
	public Object depositoTransaccion(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion {
		Cuenta cuenta = getCuenta(numeroCuenta);
		Parametro parametros = tipoParametroRepository.findBykeyParameter(ClaveParametro.VALOR_MIN_TR.getClaveParametro());
		if (valor.compareTo(new BigDecimal(parametros.getValueParameter())) < 0) {
			throw new SistemaBancarioExcepcion(
					localeResolverConfig.messageSource().getMessage("low.deposit", null, Locale.getDefault()),
					new SistemaBancarioExcepcionDetails(localeResolverConfig.messageSource().getMessage(
							"minimum.deposit", null, Locale.getDefault()), "error"));
		}
		cuentaRepository.addFounds(cuenta.getIdAccount(), valor);

		return agregarTransaccion(numeroCuenta, valor);
	}

	// Metodo para Crear transaccion
	private Transaccion agregarTransaccion(Long numeroCuenta, BigDecimal valor) {
		return transaccionRepository.save(new Transaccion(numeroCuenta, valor, TipoTransacion.DEPOSITO,
				LocalDateTime.now(), EstadoTransacion.APROBADO));
	}

	// Metodo de obtener el saldo de la cuenta.
	public BigDecimal saldoCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {
		Cuenta cuentaSeleccionada = getCuenta(numeroCuenta);
		return cuentaSeleccionada.getInitialBalance();
	}

	// Metodo Retiro de dinero
	public Transaccion retiroDinero(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion {

		Cuenta cuentaSeleccionada = getCuenta(numeroCuenta);
		validarSaldo(cuentaSeleccionada, valor);
		BigDecimal nuevoSaldo = cuentaSeleccionada.getInitialBalance().subtract(valor);
		cuentaRepository.removeFounds(cuentaSeleccionada.getIdAccount(), nuevoSaldo);

		return transaccionRepository.save(new Transaccion(numeroCuenta, valor, TipoTransacion.RETIRO,
				LocalDateTime.now(), EstadoTransacion.APROBADO));
	}

	public void validarSaldo(Cuenta cuentaSelecionada, BigDecimal valor) throws SistemaBancarioExcepcion {

		if (cuentaSelecionada.getInitialBalance().compareTo(valor) < 0) {
			transaccionRepository.save(new Transaccion(cuentaSelecionada.getNumberAccount(), valor,
					TipoTransacion.RETIRO, LocalDateTime.now(), EstadoTransacion.RECHAZADO));
			throw new SistemaBancarioExcepcion(
					localeResolverConfig.messageSource().getMessage("error.subtract", null, Locale.getDefault()),
					new SistemaBancarioExcepcionDetails(localeResolverConfig.messageSource()
							.getMessage("insufficient.balance", null, Locale.getDefault()), "error"));
		}

	}

	// Todos los movimientos de una cuenta

	public List<Transaccion> obtenerHistorialTransacciones(Long numeroCuenta) throws SistemaBancarioExcepcion {
		getCuenta(numeroCuenta);
		List<Transaccion> cuentaSelecionada = transaccionRepository.findByNumberAccount(numeroCuenta);

		if (cuentaSelecionada.isEmpty()) {
			throw new SistemaBancarioExcepcion(
					localeResolverConfig.messageSource().getMessage("not.transactions", null, Locale.getDefault()),
					new SistemaBancarioExcepcionDetails(
							localeResolverConfig.messageSource().getMessage("not.movements", null, Locale.getDefault()),
							"error"));
		}

		return cuentaSelecionada;
	}

}
