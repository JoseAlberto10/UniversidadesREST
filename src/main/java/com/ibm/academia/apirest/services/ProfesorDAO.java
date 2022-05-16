package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

public interface ProfesorDAO extends PersonaDAO
{
   	public Iterable<Persona> findProfesoresbyCarrera(String nombre);
   	public Persona actualizar(Persona profesorEncontrado, Profesor profesor);
}
