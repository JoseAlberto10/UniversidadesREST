package com.ibm.academia.apirest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@Component
public class ComandosTaller implements CommandLineRunner
{
	
	@Autowired
	@Qualifier("profesorDAOImpl")     //indicar que bean va a usar
	private PersonaDAO personaDao;
	
	@Autowired
	private CarreraDAO carreraDao;

	@Autowired
	private ProfesorDAO profesorDao;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private AulaDAO aulaDao;
	
	
	@Override
	public void run(String... args) throws Exception 

	{
		
/*ProfesorRepository:  
		 
		   Metodo
		   //"Iterable<Persona> findProfesoresByCarrera(String carrera)"
	     
			Optional<Carrera> ingSistemas = carreraDao.buscarporId(3);
			Iterable<Persona> profesoresCarrera = ((ProfesorDAO) personaDao).findProfesoresbyCarrera(ingSistemas.get().getNombre());
			profesoresCarrera.forEach(System.out::println);
*/
		
/*EmpleadoRepository:
 		
 		Metodo
	     //"Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado)"
      
			TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;
			Iterable<Persona> empleados = ((EmpleadoDAO) personaDao).findEmpleadoByTipoEmpleado(tipoEmpleado);
		    empleados.forEach(System.out::println);
*/		
		
/*	CarreraRepository:
 
	 	Metodo
	     ///"Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);"

			Optional<Persona> profesor = personaDao.buscarporId(3);
			Iterable<Carrera> carreras = carreraDao.buscarCarrerasPorProfesorNombreYApellido(profesor.get().getNombre(), profesor.get().getApellido() );
			carreras.forEach(System.out::println);
*/

/*//AulaRepository:
 
 	Metodos
		Optional<Aula> aulas = aulaDao.buscarporId(3);		
		Iterable<Aula> aulasPizarron = (Iterable<Aula>) aulaDao.buscarPorTipoPizarron(aulas.get().getPizarron());
		aulasPizarron.forEach(System.out::println);
*/
	}	
}
