package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.CarreraMapper;
import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.CarreraDAO;

@RestController
@RequestMapping("/carrera")                //Nombre del endpoint o url es igual a la clase
public class CarreraController
{
	@Autowired
	private CarreraDAO carreraDao;        //Inyectar el servicio de Carrera para poder usar el objeto carrera
	
	
	/**
	 * Endpoint para retornar lista de carreras
	 * BadRequestException En caso de que falle ya que no existen carreras
	 * @return             retorna la lista de carreras si existen
	 */
	@GetMapping("/lista/carreras")
	public List<Carrera> buscarTodas()    
	{ 
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		
		if(carreras.isEmpty())
			throw new BadRequestException("No existen carreras");       
		
		return carreras;
	}
	
	/**
	 * Endpoint para consultar(GET) una carrera por su Id
	 * @param carreraId   Indica que se pasa como parametro el id de la carrera
	 * @return            Retorna la carrera encontrada de acuerdo con el id establecido
	 * @author JARP 06/05/2022
	 */
	@GetMapping("/id/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable Integer carreraId)
	{
		Optional<Carrera> oCarrera = carreraDao.buscarporId(carreraId);
		if(!oCarrera.isPresent())                       
			throw new BadRequestException(String.format("La carrera con Id: %d no existe", carreraId));
		return oCarrera.get();  
		
		
/*		//Otra forma de hacer la peticion GET por Id
 * 
		Carrera carrera = carreraDao.buscarporId(carreraId).orElse(null);
		if(carrera == null)
			throw new BadRequestException(String.format("La carrera con Id: %d no existe", carreraId));
		
		return carrera;
*/
	}
	/*
	@PostMapping             //Metodo para crear objetos
	public ResponseEntity<?> guardarCarrera(@RequestBody Carrera carrera)  //notacion para que sea un JSON el objeto
	{
		if(carrera.getCantidadAniosEstimados() < 0)
			throw new BadRequestException("El campo cantidad de Anios debe ser mayor a 0");
		
		if(carrera.getCantidadMaterias() < 0)
			throw new BadRequestException("El campo cantidad de materias debe ser mayor a 0");
		
		Carrera carreraGuardada = carreraDao.guardar(carrera);
		
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}*/
	
	
	/**
	 * Endpoint para crear(POST) un objeto de tipo Carrera
	 * @param carrera     Objeto JSON con la informacion a modificar
	 * @param result      Seguido del objeto se indica que va a validar
	 * @return            Retorna un objeto de tipo Carrera con la informacion guardada si es valida
	 * @author JARP 06/05/2022
	 */
	@PostMapping           
	public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result)  //notacion para que sea un JSON el objeto
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

		Carrera carreraGuardada = carreraDao.guardar(carrera);
		
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}
	
	
	/**
	 * Endpoint para actualizar(PUT) un objeto de tipo Carrera por su id
	 * @param carreraId   Parametro para buscar la carrera por id
	 * @param carrera     Objeto JSON con la informacion a modificar
	 * @return            Retorna un objeto de tipo Carrera con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto carrera
	 * @author JARP 06/05/2022
	 */
	@PutMapping("/update/carreraId/{carreraId}")            
	public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera)
	{
		Optional<Carrera> oCarrera = carreraDao.buscarporId(carreraId);
		if(!oCarrera.isPresent())                       
			throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
		
		Carrera carreraActualizada = carreraDao.actualizar(oCarrera.get(), carrera);
		
		return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) una carrera por su id
	 * @param carreraId   se pasa como parametro el id de la carrera que se va a eliminar
	 * @NotFoundException En caso de que no encuentre a una carrera con el id dado 
	 * @return            Retorna la respuesta K, V de carrera eliminada
	 */
	@DeleteMapping("/delete/carreraId/{carreraId}")
	public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		Optional<Carrera> oCarrera = carreraDao.buscarporId(carreraId);
		
		if(!oCarrera.isPresent())                       
			throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
		
		carreraDao.eliminarPorId(carreraId);
		respuesta.put("OK", "Carrera ID: " + carreraId + " eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
		}
	
	
	/**
	 * Endpoint para consultar todas las carreras 
	 * @return  Retorna una lista de carreras en DTO
	 * @NotFoundException En caso de que no encuentre carreras en la BDD 	
	 * @author JARP 10/05/2022
	 */
	@GetMapping("/carreras/dto")
	public ResponseEntity<?> buscarCarrerasDTO()   
	{ 
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		
		if(carreras.isEmpty())                       
			throw new NotFoundException(String.format("No existen carreras en la base de datos"));
		
		List<CarreraDTO> listaCarreras = carreras
				.stream()
				.map(CarreraMapper::mapCarrera)
				.collect(Collectors.toList());
		return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
	}	
}
