package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO
{
	
	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados")PersonaRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado)
	{
		
		return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

	@Override
	public Persona actualizar(Persona empleadoEncontrado, Empleado empleado)
	{
		Persona empleadoActualizado = null;
		empleadoEncontrado.setNombre(empleado.getNombre());
		empleadoEncontrado.setApellido(empleado.getApellido());
		empleadoEncontrado.setDireccion(empleado.getDireccion());
		empleadoEncontrado.setDni(empleado.getDni());

		((Empleado)empleadoEncontrado).setTipoEmpleado(empleado.getTipoEmpleado());
		((Empleado)empleadoEncontrado).setSueldo(empleado.getSueldo());
		((Empleado)empleadoEncontrado).setPabellon(empleado.getPabellon());

		empleadoActualizado = repository.save(empleadoEncontrado);
		return empleadoActualizado;
	}
}
