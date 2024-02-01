package com.softwared.banco.util.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class.getName());
	
	@ExceptionHandler(value = {SistemaBancarioExcepcion.class})
	private ResponseEntity<SistemaBancarioExcepcionDetails> handleSistemaBancarioExcepcion (SistemaBancarioExcepcion excepcion){
		LOG.error(excepcion.getMessage(), excepcion);
		return new ResponseEntity<>(excepcion.getDetails(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	private ResponseEntity<SistemaBancarioExcepcionDetails> handleSistemaBancarioExcepcion (Exception excepcion){
		LOG.error(excepcion.getMessage(), excepcion);
		var detalis = new SistemaBancarioExcepcionDetails("Ha ocurrido un error inesperado", "error");
		return new ResponseEntity<>(detalis, HttpStatus.BAD_REQUEST);
	}
	
}
