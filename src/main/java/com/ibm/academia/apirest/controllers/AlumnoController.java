package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno") 
public class AlumnoController 
{
	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	/**
	 * Endpoint para crear(POST) un alumno de tipo Persona
	 * @param alumno   Objeto JSON con la informacion a crear del alumno
	 * @return         Retorna un objeto de tipo Persona con la informacion guardada del alumno y su estatus
	 * @author JARP 06/05/2022
	 */
	@PostMapping 
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno)
	{
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	} 
	
	
	/**
	 * Endpoint para consultar(GET) lista de todos los alumnos de tipo Persona
	 * NotFoundException    En caso de que no existan alumnos
	 * @return              Retorna un objeto lista de tipo Persona con los alumnos y su estatus
	 * @author JARP 06/05/2022
	 */
	@GetMapping("/lista/alumnos")
	public ResponseEntity<?> buscarTodos()    
	{ 
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		
		if(alumnos.isEmpty())
			throw new NotFoundException("No existen alumnos");       
		
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	} 
	
	
	/**
	 * Endpoint para consultar(GET) un alumno por Id
	 * @param alumnoId   Indica que se pasa como parametro el id del alumno a buscar
	 * @return           Retorna un objeto alumno de tipo Persona 
	 * @author JARP 06/05/2022
	 */
	@GetMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarporId(alumnoId);
		
		if(!oAlumno.isPresent())                       
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));

		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para actualizar(PUT) un alumno por Id
	 * @param alumnoId    Parametro para buscar el alumno por id
	 * @param alumno      Objeto JSON con la informacion a modificar
	 * @return           Retorna un objeto alumno de tipo Persona 
	 * @author JARP - 06/05/2022
	 */
	@PutMapping("/update/alumnoId/{alumnoId}")            
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarporId(alumnoId);
		
		if(!oAlumno.isPresent())                       
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) un alumno por Id
	 * @param alumnoId   Parametro para buscar el alumno por id
	 * @return           Retorna un objeto de tipo String de que ya se elimino 
	 * @author JARP - 06/05/2022
	 */
	@DeleteMapping("/delete/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarporId(alumnoId);
		
		if(!oAlumno.isPresent())                       
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		
		alumnoDao.eliminarPorId(oAlumno.get().getId());

		return new ResponseEntity<String>("Alumno Id: " + alumnoId + " se elimino correctamente", HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para asignar actualizar(PUT) una carrera a un alumno
	 * @param carreraId   id de la carrera a asignar
	 * @param alumnoId    id del alumno a quien se le va a asignar la carrera
	 * @return           Retorna un objeto alumno de tipo Persona con la nueva carrera asignada 
	 * @author JARP - 06/05/2022
	 */
	@PutMapping("/alumnoId/{alumnoId}/carreraId/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarporId(alumnoId);
		if(!oAlumno.isPresent())                       
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		
		Optional<Carrera> oCarrera = carreraDao.buscarporId(carreraId);
		if(!oCarrera.isPresent())                       
			throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
		
		Persona alumno = ((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}
}


