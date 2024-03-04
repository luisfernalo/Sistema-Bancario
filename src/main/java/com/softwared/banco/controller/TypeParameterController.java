package com.softwared.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Parameter;
import com.softwared.banco.repository.ParameterRepository;
import com.softwared.banco.util.enums.TypeParameterEnum;

@RestController
@RequestMapping("api/parameter")
public class TypeParameterController {

	@Autowired
	private ParameterRepository tipoParametroRepository;

	@PutMapping("/parameter")
	public Parameter createParameter(@RequestParam String keyParameter, @RequestParam String valueParameter) {

		return tipoParametroRepository.save(new Parameter(keyParameter, TypeParameterEnum.STRING, valueParameter));
	}

}
