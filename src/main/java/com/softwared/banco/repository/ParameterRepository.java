package com.softwared.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwared.banco.modelo.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {

	Parameter findBykeyParameter(String clave);

}
