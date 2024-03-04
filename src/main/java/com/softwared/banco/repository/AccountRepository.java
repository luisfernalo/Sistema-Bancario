package com.softwared.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.softwared.banco.modelo.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByNumberAccount(@Param("numberAccount") Long numeroCuenta);

}
