package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Carrera;

public interface CarreraDAO 
{
	public Optional<Carrera> buscarporId(Integer Id); //metodo para buscar por ID  Optional controla los nulos
	public Carrera guardar(Carrera carrera);          //metodo para guardar
	public Iterable<Carrera> buscarTodos();	          //metodo para un una lista de la carrera
	public void eliminarPorId(Integer Id);  		  //metodo para eliminar por ID
}
