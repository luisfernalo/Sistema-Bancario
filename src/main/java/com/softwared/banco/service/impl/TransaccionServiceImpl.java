package com.softwared.banco.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softwared.banco.modelo.Cuenta;
import com.softwared.banco.modelo.Transaccion;
import com.softwared.banco.repository.CuentaRepository;
import com.softwared.banco.repository.TipoParametroRepository;
import com.softwared.banco.repository.TransaccionRepository;
import com.softwared.banco.service.ITransaccionService;
import com.softwared.banco.util.enums.ClaveParametro;
import com.softwared.banco.util.enums.EstadoTransacion;
import com.softwared.banco.util.enums.TipoTransacion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcion;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;
import com.softwared.banco.util.message.LocaleResolverConfig;

@Component
public class TransaccionServiceImpl implements ITransaccionService {

	@Autowired
	private TransaccionRepository transaccionRepository;
	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private TipoParametroRepository tipoParametroRepository;

	LocaleResolverConfig localeResolverConfig = new LocaleResolverConfig();

	private Cuenta getCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {

		Optional<Cuenta> Cuenta = Optional.ofNullable(cuentaRepository.findByNumberAccount(numeroCuenta));
		Cuenta.orElseThrow(
				() -> new SistemaBancarioExcepcion(
						localeResolverConfig.messageSource().getMessage("not.found.cuenta", null, Locale.getDefault()),
						new SistemaBancarioExcepcionDetails(
								localeResolverConfig.messageSource().getMessage("invalid.cuenta", null, Locale.US),
								"error")));

		return Cuenta.get();
	}

	@Override
	public Transaccion depositoTransaccion(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion {
		Cuenta cuenta = getCuenta(numeroCuenta);
		if (compararValor(valor)) {
			return (Transaccion) modifyBalance(cuenta.getIdAccount(), numeroCuenta, valor, cuenta.getInitialBalance().add(valor),
					TipoTransacion.DEPOSITO);
		}
		return errorTrasferencer(
				localeResolverConfig.messageSource().getMessage("low.deposit", null, Locale.getDefault()),
				localeResolverConfig.messageSource().getMessage("minimum.deposit", null, Locale.getDefault()));

	}

	private Transaccion errorTrasferencer(String innerFin, String externalFin)
			throws SistemaBancarioExcepcion {
		throw new SistemaBancarioExcepcion(innerFin, new SistemaBancarioExcepcionDetails(externalFin, "error"));
	}

	private Object modifyBalance(Integer idCuenta, Long numeroCuenta, BigDecimal valor, BigDecimal valorCambiar,
			TipoTransacion tipoTrasferencia) {
		cuentaRepository.modifyBalance(idCuenta, valorCambiar);
		return addTransaction(numeroCuenta, valor, tipoTrasferencia);
	}

	private boolean compararValor(BigDecimal valor) {

		return obtenerValorMin().compareTo(valor) < 0;
	}

	private BigDecimal obtenerValorMin() {

		return new BigDecimal(tipoParametroRepository
				.findBykeyParameter(ClaveParametro.VALOR_MIN_TR.getClaveParametro()).getValueParameter());
	}

	private Transaccion addTransaction(Long numeroCuenta, BigDecimal valor, TipoTransacion tipoTransacion) {
		if (tipoTransacion == TipoTransacion.DEPOSITO) {
			return transaccionRepository.save(new Transaccion(numeroCuenta, valor, TipoTransacion.DEPOSITO,
					LocalDateTime.now(), EstadoTransacion.APROBADO));
		}

		return transaccionRepository.save(new Transaccion(numeroCuenta, valor, TipoTransacion.RETIRO,
				LocalDateTime.now(), EstadoTransacion.APROBADO));

	}

	@Override
	public BigDecimal saldoCuenta(Long numeroCuenta) throws SistemaBancarioExcepcion {
		Cuenta cuenta = getCuenta(numeroCuenta);
		return cuenta.getInitialBalance();
	}

	@Override
	public Transaccion retiroDinero(Long numeroCuenta, BigDecimal valor) throws SistemaBancarioExcepcion {

		Cuenta cuenta = getCuenta(numeroCuenta);
		if (validationWithdrawal(cuenta.getInitialBalance(), valor)) {
			transferRejected(cuenta.getNumberAccount(), valor);
			errorTrasferencer(
					localeResolverConfig.messageSource().getMessage("error.subtract", null, Locale.getDefault()),
					localeResolverConfig.messageSource().getMessage("insufficient.balance", null, Locale.getDefault()));

		}
		return (Transaccion) modifyBalance(cuenta.getIdAccount(), numeroCuenta, valor, cuenta.getInitialBalance().subtract(valor),
				TipoTransacion.RETIRO);

	}

	private Transaccion transferRejected(Long numberAccount, BigDecimal valor) {
		return transaccionRepository.save(new Transaccion(numberAccount, valor, TipoTransacion.RETIRO,
				LocalDateTime.now(), EstadoTransacion.RECHAZADO));

	}

	private boolean validationWithdrawal(BigDecimal initialBalance, BigDecimal valor) {

		return initialBalance.compareTo(valor) < 0;
	}

	@Override
	public List<Transaccion> obtenerHistorialTransacciones(Long numeroCuenta) throws SistemaBancarioExcepcion {
		getCuenta(numeroCuenta);
		List<Transaccion> cuenta = transaccionRepository.findByNumberAccount(numeroCuenta);

		if (cuenta.isEmpty()) {
			errorTrasferencer(
					localeResolverConfig.messageSource().getMessage("not.transactions", null, Locale.getDefault()),
					localeResolverConfig.messageSource().getMessage("not.movements", null, Locale.getDefault()));
		}
		return cuenta;
	}

}
