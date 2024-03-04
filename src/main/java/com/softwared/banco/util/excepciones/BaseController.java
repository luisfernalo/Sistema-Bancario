package com.softwared.banco.util.excepciones;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class.getName());

	@ExceptionHandler(value = { SistemaBancarioExcepcionDetails.class })
	private ResponseEntity<BodyResponse> handleSistemaBancarioExcepcion(SistemaBancarioExcepcionDetails excepcion) {
		LOG.error(excepcion.getUserMessage(), excepcion);
		BodyResponse response = new BodyResponse(excepcion.getUserMessage(),new Date(), excepcion.getCode().name(), excepcion.getSeverity());
		return new ResponseEntity<>(response, excepcion.getCode());
	}

	
}
