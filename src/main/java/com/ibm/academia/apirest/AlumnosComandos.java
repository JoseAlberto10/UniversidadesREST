package com.ibm.academia.apirest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@Component
public class AlumnosComandos implements CommandLineRunner
{
/*
	@Autowired
	@Qualifier("alumnoDAOImpl")        //Indica que bean voy a usar ya que está heredando de PersonaDAOImpl
	private PersonaDAO personaDao;     //Inyectar dependencia
	
	@Autowired
	private CarreraDAO carreraDao;      //Inyectar dependencia o servicio para poder usar el objeto

	@Autowired
	private AlumnoDAO alumnoDao;
*/	
	@Override
	public void run(String... args) throws Exception 
	{
/*		
		Optional<Carrera> ingSistemas = carreraDao.buscarporId(3);
		
		Iterable<Persona> alumnos = personaDao.buscarTodos();
		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()));
		alumnos.forEach(alumno -> personaDao.guardar(alumno));
*/		

		
/*
		Optional<Persona> alumno = alumnoDao.buscarporId(1);
		
		Optional<Persona> personaDni = personaDao.buscarPorDni(alumno.get().getDni());
		System.out.println("DNI: " + personaDni.toString());
*/
		

/*		Optional<Persona> alumno = alumnoDao.buscarporId(1);
		
		Iterable<Persona> personasApellido = personaDao.buscarPersonaPorApellido(alumno.get().getApellido());
		personasApellido.forEach(System.out::println);
*/
	}

}
