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

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController
{
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Autowired
	private AulaDAO aulaDao;
	
	/**
	 * Endpoint para consultar(GET) lista de todos los pabellones
	 * NotFoundException    En caso de que no existan pabellones
	 * @return              Retorna un objeto lista de tipo Pabellon con los pabellones y su estatus
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/lista/pabellones")
	public ResponseEntity<?> buscarTodos()    
	{ 
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");       
		
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para consultar(GET) un pabellon por Id
	 * @param pabellonId   Indica que se pasa como parametro el id del pabellon a buscar
	 * @return             Retorna un objeto profesor de tipo Pabellon 
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> obtenerPabellonPorId(@PathVariable Integer pabellonId)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarporId(pabellonId);
		
		if(!oPabellon.isPresent())                       
			throw new NotFoundException(String.format("El pabellon con Id: %d no existe", pabellonId));

		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para crear(POST) un objeto de tipo Pabellon
	 * @param pabellon Objeto JSON con la informacion a crear
	 * @param result   Seguido del objeto se indica lo que va a validar
	 * @return         Retorna un objeto de tipo Pabellon con la informacion guardada si es valida
	 */
	@PostMapping
    public ResponseEntity<?> crearPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result)
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
	
		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
    }
	
	
	/**
	 * Endpoint para actualizar(PUT) un objeto de tipo Pabellon por su id
	 * @param pabellonId  Parametro para buscar el aula por id
	 * @param pabellon    Objeto JSON con la informacion a modificar
	 * @return            Retorna un objeto de tipo Pabellon con la informacion actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto pabellon
	 * @author JARP 16/05/2022
	 */
	@PutMapping("/update/pabellonId/{pabellonId}")            
	public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarporId(pabellonId);
		if(!oPabellon.isPresent())                       
			throw new NotFoundException(String.format("El aula con Id: %d no existe", pabellonId));
		
		Pabellon pabellonActualizado = pabellonDao.actualizar(oPabellon.get(), pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) un pabellon por su id
	 * @param pabellonId  se pasa como parametro el id del aula que se va a eliminar
	 * @NotFoundException En caso de que no encuentre al pabellon con el id dado 
	 * @return            Retorna la respuesta K, V de pabellon eliminada
	 */
	@DeleteMapping("/delete/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();	
		Optional<Pabellon> oPabellon = pabellonDao.buscarporId(pabellonId);
		
		if(!oPabellon.isPresent())                       
			throw new NotFoundException(String.format("El pabellon con Id: %d no existe", pabellonId));
		
        List<Aula> aulas = (List<Aula>) aulaDao.buscarPorNombrePabellon(oPabellon.get().getNombre());
        aulas.forEach(aula -> aulaDao.eliminarPorId(aula.getId()));
		
		pabellonDao.eliminarPorId(pabellonId);
		respuesta.put("OK", "Pabellon ID: " + pabellonId + " eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
		}
}
