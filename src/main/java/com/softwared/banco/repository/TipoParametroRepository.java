package com.softwared.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwared.banco.modelo.Parametro;

public interface TipoParametroRepository extends JpaRepository<Parametro, Integer> {

	Parametro findBykeyParameter(String clave);
	
	Parametro findByValueParameter(String valor);
}
