package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;


@DataJpaTest      //NOTACION para insertar en la Base de datos
public class CarreraRepositoryTest 
{
	@Autowired   
	private CarreraRepository carreraRepository; 
	
	
	@BeforeEach
	void SetUp()
	{
		//GIVEN   --> datos precondiciones para el test
		carreraRepository.save(DatosDummy.carrera01());
		carreraRepository.save(DatosDummy.carrera02());
		carreraRepository.save(DatosDummy.carrera03());
	}
	
	@AfterEach
	void tearDown()
	{
		carreraRepository.deleteAll();
	}
	
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarrerasByNombreContains()
	{
		//GIVEN   --> datos precondiciones para el test
	  /*carreraRepository.save(DatosDummy.carrera01());
		carreraRepository.save(DatosDummy.carrera02());
		carreraRepository.save(DatosDummy.carrera03());*/
		
		//WHEN   --> Ejecutar la accion a probar 
		Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("sistemas");
		
		//THEN  --> Validar que lo que se está probando funciona correctamente
		assertThat(((List<Carrera>)expected).size() == 2).isTrue();
	}
	
	
	@Test
	//@Disabled    //omite a este metodo para que solo se ejecute el anterior
	@DisplayName("Test: Buscar carreras por nombre NO CASE Sensitive")
	void findCarrerasByNombreContainsIgnoreCase()
	{
		//GIVEN   --> datos
	  /*carreraRepository.save(DatosDummy.carrera01());
		carreraRepository.save(DatosDummy.carrera02());
	    carreraRepository.save(DatosDummy.carrera03());*/
		
		//WHEN   --> Ejecutar la accion
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("sistemas");
		
		//THEN  --> Validar
		assertThat(expected.size() == 2).isTrue();
	}
	
	
	@Test
	@DisplayName("Test: Buscar carreras mayores a N anios")
	void findCarrerasByCantidadAniosEstimadosAfter()
	{
		//GIVEN   --> datos
	  /*carreraRepository.save(DatosDummy.carrera01());
		carreraRepository.save(DatosDummy.carrera02());
		carreraRepository.save(DatosDummy.carrera03());*/
		
		//WHEN   --> Ejecutar la accion
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosEstimadosAfter(4);
		
		//THEN  --> Validar
		assertThat(expected.size() == 2).isTrue();
	}
}
