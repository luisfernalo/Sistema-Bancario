package com.softwared.banco.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.softwared.banco.modelo.Cuenta;


public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

	Cuenta findByNumberAccount(Long numeroCuenta);
	
    Optional<Cuenta> findByHolderEmail(String holderEmail);

	@Transactional
	@Modifying
	@Query(value = "UPDATE CUENTA SET initial_balance=initial_balance+:valorDeposito where ID_CUENTA=:IdCuenta", nativeQuery = true)
	void addFounds(@Param("IdCuenta") Integer idCuenta, @Param("valorDeposito") BigDecimal valorDeposito);

	@Transactional
	@Modifying
	@Query(value = "UPDATE CUENTA SET initial_balance=:valorRetiro where ID_CUENTA=:IdCuenta", nativeQuery = true)
	void removeFounds(@Param("IdCuenta") Integer idCuenta, @Param("valorRetiro") BigDecimal valorRetiro);

}
