package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;

public interface AlumnoDAO 
{
	public Optional<Persona> buscarporId(Integer Id); //metodo para buscar por ID  Optional controla los nulos
	public Persona guardar(Persona persona);          //metodo para guardar
	public Iterable<Persona> buscarTodos();	          //metodo para un una lista de la carrera
	public void eliminarPorId(Integer Id);  		  //metodo para eliminar por ID
}
