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
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado") 
public class EmpleadoController 
{
    @Autowired
    @Qualifier("empleadoDAOImpl")
    private PersonaDAO empleadoDao;
    
    
	/**
	 * Endpoint para consultar(GET) lista de todos los empleados de tipo Persona
	 * NotFoundException    En caso de que no existan empleados
	 * @return              Retorna un objeto lista de tipo Persona con los empleados y su estatus
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/lista/empleados")
	public ResponseEntity<?> buscarTodos()    
	{ 
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados");       
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para consultar(GET) un empleado por Id
	 * @param profesorId   Indica que se pasa como parametro el id del profesor a buscar
	 * @return             Retorna un objeto profesor de tipo Persona 
	 * @author JARP 16/05/2022
	 */
	@GetMapping("/empleadoId/{empleadoId}")
	public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer empleadoId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarporId(empleadoId);
		
		if(!oEmpleado.isPresent())                       
			throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));

		return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para crear(POST) un objeto empleado de tipo Persona
	 * @param empleado Objeto JSON con la informacion a crear
	 * @param result   Seguido del objeto se indica lo que va a validar
	 * @return         Retorna un objeto de tipo Persona con la informacion guardada si es valida
	 * @author JARP - 16/05/2022
	 */
	@PostMapping
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result)
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
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
    }
	
	
	/**
	 * Endpoint para actualizar(PUT) un empleado por Id
	 * @param empleadoId  Parametro para buscar el empleado por id
	 * @param empleado    Objeto JSON con la informacion a modificar
	 * @return            Retorna un objeto empleado de tipo Persona 
	 * @author JARP - 16/05/2022
	 */
	@PutMapping("/update/empleadoId/{empleadoId}")            
	public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer empleadoId, @RequestBody Empleado empleado)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarporId(empleadoId);
		
		if(!oEmpleado.isPresent())                       
			throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
		
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(oEmpleado.get(), empleado);
		return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para eliminar(DELETE) un empleado por Id
	 * @param empleadoId Parametro para buscar el profesor por id
	 * @return           Retorna un objeto de tipo String de que ya se elimino 
	 * @author JARP - 16/05/2022
	 */
	@DeleteMapping("/delete/empleadoId/{empleadoId}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarporId(empleadoId);
		
		if(!oEmpleado.isPresent())                       
			throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
		
		empleadoDao.eliminarPorId(oEmpleado.get().getId());

		return new ResponseEntity<String>("Empleado Id: " + empleadoId + " se elimino correctamente", HttpStatus.OK);
	}

}
