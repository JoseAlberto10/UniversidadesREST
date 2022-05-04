package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class AlumnoDAOImpl implements AlumnoDAO
{
	@Autowired
	@Qualifier("repositorioAlumnos")   //Esto inyecta el repositorio de alumno ya que el de persona no es un bean y no puede inyectarse
	private PersonaRepository personaRepository;    //Inyectar el repositorio de la clase Padre
	
	@Override
	public Optional<Persona> buscarporId(Integer Id) 
	{
		
		return null;
	}

	@Override
	public Persona guardar(Persona persona)
	{
		
		return null;
	}

	@Override
	public Iterable<Persona> buscarTodos() 
	{
		
		return null;
	}

	@Override
	public void eliminarPorId(Integer Id) 
	{
		
		
	}   

}
