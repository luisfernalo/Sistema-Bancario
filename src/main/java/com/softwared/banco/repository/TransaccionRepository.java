package com.softwared.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softwared.banco.modelo.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

	List<Transaccion> findByNumeroCuenta(Long numeroCuenta);

}
