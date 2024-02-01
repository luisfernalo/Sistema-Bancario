package com.softwared.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwared.banco.modelo.Parametro;

public interface TipoParametroRepository extends JpaRepository<Parametro, Integer> {

	Parametro findByClave(String clave);
	
	Parametro findByValor(String valor);
}
