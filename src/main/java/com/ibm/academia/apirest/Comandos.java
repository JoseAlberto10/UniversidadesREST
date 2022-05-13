package com.ibm.academia.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@Component
public class Comandos implements CommandLineRunner
{
	/*
	@Autowired
	private CarreraDAO carreraDao;
	
	@Autowired
	@Qualifier("profesorDAOImpl")     //indicar que bean va a usar
	private PersonaDAO personaDao;
*/
	
	@Override
	public void run(String... args) throws Exception {
		
/*		Carrera ingSistemas = new Carrera(null,"Ingenieria en sistemas", 60, 5);
		Carrera ingIndustrial = new Carrera(null,"Ingenieria Industrial", 55, 5);
		Carrera ingAlimentos = new Carrera(null,"Ingenieria en Alimentos", 53, 5);
		Carrera ingElectronica = new Carrera(null,"Ingenieria Electronica", 45, 5);
		Carrera licSistemas = new Carrera(null,"Licenciatura en Sistemas", 40, 4);
		Carrera licTurismo = new Carrera(null,"Licenciatura en Turismo", 42, 4);
		Carrera licYoga = new Carrera(null,"Licenciatura en Yoga", 25, 3);
		Carrera licRecursos = new Carrera(null,"Licenciatura en Recursos", 33, 3);
		
		carreraDao.guardar(ingSistemas);
		carreraDao.guardar(ingIndustrial);
		carreraDao.guardar(ingAlimentos);
		carreraDao.guardar(ingElectronica);
		carreraDao.guardar(licSistemas);
		carreraDao.guardar(licTurismo);
		carreraDao.guardar(licYoga);
		carreraDao.guardar(licRecursos);
*/		
		
		
		
//// ---------Comprobacion de que los metodos de consulta JPQL y Nativos si sirven---------------------------		

/*		Optional<Carrera> ingSistemas = carreraDao.buscarporId(3);
		
		Iterable<Persona> alumnos = personaDao.buscarTodos();
		alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()));
		alumnos.forEach(alumno -> personaDao.guardar(alumno));
*/		
		
/*		
		Optional<Carrera> ingSistemas = carreraDao.buscarporId(3);
		
		Iterable<Persona> alumnosCarrera = ((AlumnoDAO) personaDao).buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
		alumnosCarrera.forEach(System.out::println);
*/		

/*		
		List<Carrera> carreras = (List<Carrera>)carreraDao.findCarrerasByNombreContains("Sistemas");
		carreras.forEach(System.out::println);
*/		

/*		
 		List<Carrera> carrerasIgnoreCase1 = (List<Carrera>)carreraDao.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
 		List<Carrera> carrerasIgnoreCase2 = (List<Carrera>)carreraDao.findCarrerasByNombreContainsIgnoreCase("sistemas");
 		carrerasIgnoreCase1.forEach(System.out::println);
 		carrerasIgnoreCase2.forEach(System.out::println);
*/
		
/*	
 		List<Carrera> carrerasPorAnio = (List<Carrera>)carreraDao.findCarrerasByCantidadAniosEstimadosAfter(3);
 		carrerasPorAnio.forEach(System.out::println);
*/

/*		
		Optional<Persona> persona = personaDao.buscarporId(1);
		System.out.println(persona.toString());
*/		

		
		
///Creacion de objetos ------------------------------------------------------------------------------
		
/*   	Carrera sistemas = new Carrera(null,"Ingenieria en sistemas", 22, 3);
		Carrera carreraGuardada = carreraDao.guardar(sistemas);
		System.out.println(carreraGuardada.toString());
		
		Carrera finanzas = new Carrera(null,"Ingenieria en finanzas", 20, 3);
		Carrera carreraGuardada = carreraDao.guardar(finanzas);
		System.out.println(carreraGuardada.toString()); 
		
		
		Carrera carrera = null;
		
		Optional<Carrera> oCarrera = carreraDao.buscarporId(1);
		
		if(oCarrera.isPresent()) 
		{
			carrera = oCarrera.get();
			System.out.println(carrera.toString());
		}
		else 
		{
			System.out.println("Carrera no encontrada");
		}
		
		carrera.setCantidadAniosEstimados(4);
		carrera.setCantidadMaterias(30);
		carreraDao.guardar(carrera);
		
		System.out.println(carreraDao.buscarporId(7).orElse(new Carrera()).toString());
		
		
		carreraDao.eliminarPorId(2);
		System.out.println(carreraDao.buscarporId(2).orElse(new Carrera()).toString());
		
*/
	}
}
