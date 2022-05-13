package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;

@DataJpaTest   
public class AlumnoRepositoryTest
{
	
	@Autowired
	@Qualifier("repositorioAlumnos")    //indicar que bean va a usar
	private PersonaRepository alumnoRepository;
	
	@Autowired   
	private CarreraRepository carreraRepository; 
	
	
	@Test
	@DisplayName("Test: Buscar alumnos por nombre Carrera")
	void buscarAlumnoPorNombreCarrera()
	{
		//GIVEN

		Iterable<Persona> personas = alumnoRepository.saveAll(
				Arrays.asList(
						DatosDummy.alumno01(),
						DatosDummy.alumno02(),
						DatosDummy.alumno03()
						)
					);
		
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01()); //Debo asignar la carrera a cada alumno
		personas.forEach(alumno -> ((Alumno)alumno).setCarrera(carrera));
		alumnoRepository.saveAll(personas);
		
		//WHEN
		String carreraNombre = "Ingenieria en sistemas";
		List<Persona> expected = (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(carreraNombre);
		
		//THEN  --> Validar
		assertThat(expected.size() == 3).isTrue();
	}
	
	
	@Test
	@DisplayName("Test: Buscar alumnos por nombre Carrera sin valores")
	void buscarAlumnoPorNombreCarreraSinValores()
	{
		//GIVEN

		Iterable<Persona> personas = alumnoRepository.saveAll(
				Arrays.asList(
						DatosDummy.alumno01(),
						DatosDummy.alumno02(),
						DatosDummy.alumno03()
						)
					);
		
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01()); //Debo asignar la carrera a cada alumno
		personas.forEach(alumno -> ((Alumno)alumno).setCarrera(carrera));
		alumnoRepository.saveAll(personas);
		
		//WHEN
		String carreraNombre = "Ingenieria en Alimentos";
		List<Persona> expected = (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(carreraNombre);
		
		//THEN  --> Validar
		assertThat(expected.isEmpty()).isTrue();   //Ingenieria en Alimentos no existe, por lo cual es vacio
	}
}
