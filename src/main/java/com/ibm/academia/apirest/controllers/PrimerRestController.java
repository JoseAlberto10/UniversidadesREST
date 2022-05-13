package com.ibm.academia.apirest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                         //Indica que es un controlador y ya puedo usar endpoints
@RequestMapping("/restapi")             //Asignarle un valor a la URL peticion
public class PrimerRestController  
{

	Logger logger = LoggerFactory.getLogger(PrimerRestController.class);   //Todos los controladores y DAOs implementados deben tener esto por si hay algun error para que lo envie aqui
	
	@GetMapping                      //Indicar el metodo HTTP, en este caso Consultar o READ
	public String holaMundo() {
		
		logger.trace("trace log");
		logger.debug("debug log");
		logger.info("info log");
		logger.warn("warn log");
		logger.error("error log");
		return "Hola Mundo API";
	}
}
