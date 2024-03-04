package com.softwared.banco.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.softwared.banco.modelo.Account;
import com.softwared.banco.modelo.Transaction;
import com.softwared.banco.repository.AccountRepository;
import com.softwared.banco.repository.ParameterRepository;
import com.softwared.banco.repository.TransactionRepository;
import com.softwared.banco.service.ITransactionService;
import com.softwared.banco.util.enums.KeyParameterEnum;
import com.softwared.banco.util.enums.TransactionStatusEnum;
import com.softwared.banco.util.enums.TransactionTypeEnum;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;
import com.softwared.banco.util.message.LocaleResolverConfig;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private final TransactionRepository transaccionRepository;

	private final AccountRepository cuentaRepository;

	private final ParameterRepository tipoParametroRepository;

	private final LocaleResolverConfig localeResolverConfig;

	public TransactionServiceImpl(TransactionRepository transaccionRepository, AccountRepository cuentaRepository,
			ParameterRepository tipoParametroRepository, LocaleResolverConfig localeResolverConfig) {
		super();
		this.transaccionRepository = transaccionRepository;
		this.cuentaRepository = cuentaRepository;
		this.tipoParametroRepository = tipoParametroRepository;
		this.localeResolverConfig = localeResolverConfig;
	}

	private Account getAccountInfo(Long numeroCuenta) {

		Optional<Account> cuenta = cuentaRepository.findByNumberAccount(numeroCuenta);

		if (cuenta.isEmpty()) {
			globalError(localeResolverConfig.messageSource().getMessage("not.found.cuenta", null, Locale.getDefault()),
					HttpStatus.NOT_FOUND);
		}

		return cuenta.get();
	}

	@Override
	public Transaction depositTransaction(Long numeroCuenta, BigDecimal valor) {

		Account cuenta = getAccountInfo(numeroCuenta);

		if (compareValue(valor))
			globalError(localeResolverConfig.messageSource().getMessage("minimum.deposit", null, Locale.getDefault()),
					HttpStatus.PRECONDITION_FAILED);

		cuenta.setInitialBalance(cuenta.getInitialBalance().add(valor));

		return modifyBalance(cuenta, numeroCuenta, valor, TransactionTypeEnum.DEPOSITO);

	}

	private Transaction modifyBalance(Account cuenta, Long numeroCuenta, BigDecimal valor,
			TransactionTypeEnum tipoTrasferencia) {
		cuentaRepository.save(cuenta);
		return addTransaction(numeroCuenta, valor, tipoTrasferencia);
	}

	private boolean compareValue(BigDecimal valor) {

		return getMinimumValue().compareTo(valor) > 0;
	}

	private BigDecimal getMinimumValue() {

		return new BigDecimal(tipoParametroRepository
				.findBykeyParameter(KeyParameterEnum.VALOR_MIN_TR.getkeyParameterEnum()).getValueParameter());
	}

	private Transaction addTransaction(Long numeroCuenta, BigDecimal valor, TransactionTypeEnum tipoTransacion) {
		if (tipoTransacion.equals(TransactionTypeEnum.DEPOSITO)) {
			return transaccionRepository.save(new Transaction(numeroCuenta, valor, TransactionTypeEnum.DEPOSITO,
					LocalDateTime.now(), TransactionStatusEnum.APROBADO));
		}

		return transaccionRepository.save(new Transaction(numeroCuenta, valor, TransactionTypeEnum.RETIRO,
				LocalDateTime.now(), TransactionStatusEnum.APROBADO));

	}

	@Override
	public BigDecimal accountBalance(Long numeroCuenta) {
		Account cuenta = getAccountInfo(numeroCuenta);
		return cuenta.getInitialBalance();
	}

	@Override
	public Transaction withdrawal(Long numeroCuenta, BigDecimal valor) {

		Account cuenta = getAccountInfo(numeroCuenta);
		if (validationWithdrawal(cuenta.getInitialBalance(), valor)) {
			transferRejected(cuenta.getNumberAccount(), valor);
			globalError(
					localeResolverConfig.messageSource().getMessage("insufficient.balance", null, Locale.getDefault()),
					HttpStatus.BAD_REQUEST);

		}
		cuenta.setInitialBalance(cuenta.getInitialBalance().subtract(valor));
		return modifyBalance(cuenta, numeroCuenta, valor, TransactionTypeEnum.RETIRO);

	}

	private Transaction transferRejected(Long numberAccount, BigDecimal valor) {
		return transaccionRepository.save(new Transaction(numberAccount, valor, TransactionTypeEnum.RETIRO,
				LocalDateTime.now(), TransactionStatusEnum.RECHAZADO));

	}

	private boolean validationWithdrawal(BigDecimal initialBalance, BigDecimal valor) {

		return initialBalance.compareTo(valor) < 0;
	}

	@Override
	public List<Transaction> getTransactionHistory(Long numeroCuenta) {
		getAccountInfo(numeroCuenta);
		List<Transaction> cuenta = transaccionRepository.findByNumberAccount(numeroCuenta);

		if (cuenta.isEmpty()) {
			globalError(localeResolverConfig.messageSource().getMessage("not.movements", null, Locale.getDefault()),
					HttpStatus.NOT_FOUND);
		}
		return cuenta;
	}

	private void globalError(String errorMessage, HttpStatus status) {
		throw new SistemaBancarioExcepcionDetails(errorMessage, "error", status);
	}

}
