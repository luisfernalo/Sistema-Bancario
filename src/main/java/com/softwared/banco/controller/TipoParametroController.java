package com.softwared.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwared.banco.modelo.Parametro;
import com.softwared.banco.repository.TipoParametroRepository;
import com.softwared.banco.util.enums.TipoParametro;

@RestController
@RequestMapping("api/parametro")
public class TipoParametroController {

	@Autowired
	private TipoParametroRepository tipoParametroRepository;

	@PutMapping("/parametro")
	public Parametro crearParametro(@RequestParam String keyParameter, @RequestParam String valueParameter) {

		return tipoParametroRepository.save(new Parametro(keyParameter, TipoParametro.STRING, valueParameter));
	}

}
