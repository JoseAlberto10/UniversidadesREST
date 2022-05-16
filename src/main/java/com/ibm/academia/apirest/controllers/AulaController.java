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
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula") 
public class AulaController 
{
	@Autowired
	private AulaDAO aulaDao;
	
	
	/**
	 * Endpoint para retornar lista de todas las aulas
	 * BadRequestException  En caso de que falle ya que no existen aulas
	 * @return              Retorna la lista de aulas si existen
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/lista/aulas")
	public List<Aula> buscarTodas()    
	{ 
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		
		if(aulas.isEmpty())
			throw new BadRequestException("No existen carreras");       
		
		return aulas;
	}
	
	
	/**
	 * Endpoint para consultar(GET) un aula por su Id
	 * @param aulaId      Indica que se pasa como parametro el id del aula
	 * @return            Retorna el aula encontrada de acuerdo con el id establecido
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/id/{aulaId}")
	public Aula buscarAulaPorId(@PathVariable Integer aulaId)
	{
		Optional<Aula> oAula = aulaDao.buscarporId(aulaId);
		if(!oAula.isPresent())                       
			throw new BadRequestException(String.format("El aula con Id: %d no existe", aulaId));
		return oAula.get();  
		
/*		//Otra forma de hacer la peticion GET por Id
 
		Aula aula = aulaDao.buscarporId(aulaId).orElse(null);
		if(aula == null)
			throw new BadRequestException(String.format("El aula con Id: %d no existe", aulaId));
		
		return aula;
*/
	}
	
	
	/**
	 * Endpoint para crear(POST) un objeto de tipo Aula
	 * @param aula     Objeto JSON con la informacion a crear
	 * @param result   Seguido del objeto se indica lo que va a validar
	 * @return         Retorna un objeto de tipo Aula con la informacion guardada si es valida
	 */
	@PostMapping
    public ResponseEntity<?> crearAula(@Valid @RequestBody Aula aula, BindingResult result)
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
	
        Aula aulaGuardada = aulaDao.guardar(aula);
        return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
    }
	
	
	/**
	 * Endpoint para actualizar(PUT) un objeto de tipo Aula por su id
	 * @param aulaId      Parametro para buscar el aula por id
	 * @param aula        Objeto JSON con la informacion a modificar
	 * @return            Retorna un objeto de tipo Aula con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto aula
	 * @author JARP 16/05/2022
	 */
	@PutMapping("/update/aulaId/{aulaId}")            
	public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula)
	{
		Optional<Aula> oAula = aulaDao.buscarporId(aulaId);
		if(!oAula.isPresent())                       
			throw new NotFoundException(String.format("El aula con Id: %d no existe", aulaId));
		
		Aula aulaActualizada = aulaDao.actualizar(oAula.get(), aula);
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) un aula por su id
	 * @param aulaId   se pasa como parametro el id del aula que se va a eliminar
	 * @NotFoundException En caso de que no encuentre al aula con el id dado 
	 * @return            Retorna la respuesta K, V de aula eliminada
	 */
	@DeleteMapping("/delete/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		Optional<Aula> oAula = aulaDao.buscarporId(aulaId);
		
		if(!oAula.isPresent())                       
			throw new NotFoundException(String.format("El aula con Id: %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		respuesta.put("OK", "Aula ID: " + aulaId + " eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
		}
}
