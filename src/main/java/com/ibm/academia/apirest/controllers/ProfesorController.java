package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@RestController
@RequestMapping("/profesor") 
public class ProfesorController
{

	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	
	/**
	 * Endpoint para consultar(GET) lista de todos los profesores de tipo Persona
	 * NotFoundException    En caso de que no existan profesores
	 * @return              Retorna un objeto lista de tipo Persona con los profesores y su estatus
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/lista/profesores")
	public ResponseEntity<?> buscarTodos()    
	{ 
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		
		if(profesores.isEmpty())
			throw new NotFoundException("No existen profesores");       
		
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para consultar(GET) un profesor por Id
	 * @param profesorId   Indica que se pasa como parametro el id del profesor a buscar
	 * @return             Retorna un objeto profesor de tipo Persona 
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Integer profesorId)
	{
		Optional<Persona> oProfesor = profesorDao.buscarporId(profesorId);
		
		if(!oProfesor.isPresent())                       
			throw new NotFoundException(String.format("El profesor con Id: %d no existe", profesorId));

		return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para crear(POST) un objeto profesor de tipo Persona
	 * @param profesor Objeto JSON con la informacion a crear
	 * @param result   Seguido del objeto se indica lo que va a validar
	 * @return         Retorna un objeto de tipo Persona con la informacion guardada si es valida
	 * @author JARP - 16/05/2022
	 */
	@PostMapping
    public ResponseEntity<?> crearProfesor(@Valid @RequestBody Profesor profesor, BindingResult result)
    {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
    }
	
	
	/**
	 * Endpoint para actualizar(PUT) un profesor por Id
	 * @param profesorId  Parametro para buscar el profesor por id
	 * @param profesor    Objeto JSON con la informacion a modificar
	 * @return            Retorna un objeto profesor de tipo Persona 
	 * @author JARP - 16/05/2022
	 */
	@PutMapping("/update/profesorId/{profesorId}")            
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Profesor profesor)
	{
		Optional<Persona> oProfesor = profesorDao.buscarporId(profesorId);
		
		if(!oProfesor.isPresent())                       
			throw new NotFoundException(String.format("El profesor con Id: %d no existe", profesorId));
		
		Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(oProfesor.get(), profesor);
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) un profesor por Id
	 * @param profesorId Parametro para buscar el profesor por id
	 * @return           Retorna un objeto de tipo String de que ya se elimino 
	 * @author JARP - 16/05/2022
	 */
	@DeleteMapping("/delete/profesorId/{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Integer profesorId)
	{
		Optional<Persona> oProfesor = profesorDao.buscarporId(profesorId);
		
		if(!oProfesor.isPresent())                       
			throw new NotFoundException(String.format("El profesor con Id: %d no existe", profesorId));
		
		profesorDao.eliminarPorId(oProfesor.get().getId());

		return new ResponseEntity<String>("Profesor Id: " + profesorId + " se elimino correctamente", HttpStatus.OK);
	}
	
}
