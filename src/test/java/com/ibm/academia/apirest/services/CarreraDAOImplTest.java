package com.ibm.academia.apirest.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

public class CarreraDAOImplTest 
{ 
	
	//Mockito simula la inyeccion del repositorioCarrera
	
	CarreraDAO carreraDao;
	CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp()
	{
		carreraRepository = mock(CarreraRepository.class);
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	
	@Test
	@DisplayName("Test: Buscar carreras que contengan tal nombre")
	void findCarrerasByNombreContains()
	{
		//GIVEN
		String nombre = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContains(nombre))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//WHEN
	    List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombre);
	    
	    //THEN
	    assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
	    assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
	    
	    verify(carreraRepository).findCarrerasByNombreContains(nombre);
	}
	
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre NO CASE sensitive")
	void findCarrerasByNombreContainsIgnoreCase()
	{
		//GIVEN
		String nombre = "ingenieria";
		when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//WHEN
	    List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombre);
	    
	    //THEN
	    assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
	    assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
	    
	    verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
	}
	

	@Test
	@DisplayName("Test: Buscar carreras despues de N años")
	void findCarrerasByCantidadAniosEstimadosAfter()
	{
		
		//GIVEN
		Integer cantidad = 4;
		when(carreraRepository.findCarrerasByCantidadAniosEstimadosAfter(cantidad))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));
		
		//WHEN
	    List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosEstimadosAfter(cantidad);
	    
	    //THEN
	    assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
	    assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
	    
	    verify(carreraRepository).findCarrerasByCantidadAniosEstimadosAfter(cantidad);

	}
}
