package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@DataJpaTest 
public class PersonaRepositoryTest 
{

	@Autowired
	@Qualifier("repositorioAlumnos")    //indicar que bean va a usar
	private PersonaRepository alumnoRepository;
	
	@Autowired
	@Qualifier("repositorioEmpleados")
	private PersonaRepository empleadoRepository;
	
	@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesorRepository;
	
	
	@Test
	@DisplayName("Test: Buscar por Nombre y Apellido")
	void buscarPorNombreYApellido()
	{
		//GIVEN
		Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());     
		
		//When
		Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(), DatosDummy.empleado01().getApellido());

		//Then
		assertThat(expected.get()).isInstanceOf(Empleado.class);
		assertThat(expected.get()).isEqualTo(personaEmpleado);
	}
	
	
	@Test
	@DisplayName("Test: Buscar persona por DNI")
	void buscarPorDni()
	{
		//GIVEN
		Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());  
		
		//When
		Optional<Persona> expected = profesorRepository.buscarPorDni(DatosDummy.profesor01().getDni());

		//Then
		assertThat(expected.get()).isInstanceOf(Profesor.class);
		assertThat(expected.get()).isEqualTo(personaProfesor);
		assertThat(expected.get().getDni()).isEqualTo(personaProfesor.getDni());
	}
	
	
	@Test
	@DisplayName("Test: Buscar persona por Apellido")
	void buscarPersonaPorApellido()
	{
		//GIVEN
		List<Persona> listaPersonas = new ArrayList<Persona>(); 
		listaPersonas.add(DatosDummy.alumno01());
		listaPersonas.add(DatosDummy.alumno02());
		listaPersonas.add(DatosDummy.alumno03());
		Iterable<Persona> personas = alumnoRepository.saveAll(listaPersonas);
		
/*		Iterable<Persona> listaPersonas = alumnoRepository.saveAll(
				Arrays.asList(
						DatosDummy.alumno01(),
						DatosDummy.alumno02(),
						DatosDummy.alumno03()
						);
				)  
*/
		//When
		String apellido = "Benitez";
		List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonaPorApellido(apellido);
		
		//THEN  --> Validar
		assertThat(expected.size() == 2).isTrue();
	}
}
