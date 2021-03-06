package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO
{

	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesores")PersonaRepository repository)
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findProfesoresbyCarrera(String nombre)
	{
		return ((ProfesorRepository)repository).findProfesoresbyCarrera(nombre);
	}

	@Override
	public Persona actualizar(Persona profesorEncontrado, Profesor profesor) 
	{
		Persona profesorActualizado = null;
		profesorEncontrado.setNombre(profesor.getNombre());
		profesorEncontrado.setApellido(profesor.getApellido());
		profesorEncontrado.setDireccion(profesor.getDireccion());
		profesorEncontrado.setDni(profesor.getDni());
		((Profesor)profesorEncontrado).setSueldo(profesor.getSueldo());
		((Profesor)profesorEncontrado).setCarreras(profesor.getCarreras());
		profesorActualizado = repository.save(profesorEncontrado);
		return profesorActualizado;
	}
}
