package com.ibm.academia.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@SpringBootApplication
public class UniversidadesRestApplication
{
	/*
	@Autowired
	private CarreraDAO carreraDao;
*/
	public static void main(String[] args) 
	{
		SpringApplication.run(UniversidadesRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner()
	{
		return args ->{
			/*
			Direccion direccion = new Direccion("Lirios", "9A", "54987", "4", "2", "Coacalco");
			Persona alumno = new Alumno(null, "Jose", "Ramirez", "1008000", direccion);
			
			Persona personaGuardada = alumnoDao.guardar(alumno);
			System.out.println(personaGuardada.toString()); 
			
			List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
			alumnos.forEach(System.out::println);
			
			
			Optional<Carrera> oCarrera = carreraDao.buscarporId(1);
			System.out.println(oCarrera.toString());
			*/
	/*	----------------------------------------------------------------------------este lo HICE YO	
			Profesor profesor = null;
			
			Optional<Persona> oProfesor = profesorDao.buscarporId(1);
			
			if(oProfesor.isPresent()) 
			{
				profesor = (Profesor) oProfesor.get();
				System.out.println(profesor.toString());
			}
			else 
			{
				System.out.println("Profesor no encontrado");
			}
			Direccion direccion = new Direccion("Petunias", "2A", "54227", "2", "3", "Tultepec");
			profesor.setNombre("Juan");
			profesor.setApellido("Calderon");
			profesor.setDireccion(direccion);
			profesor.setSueldo(null);
			profesorDao.guardar(profesor);
	*/
		};
	}
}
