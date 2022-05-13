package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Persona;

public interface ProfesorDAO extends PersonaDAO
{
   	public Iterable<Persona> findProfesoresbyCarrera(String nombre);
}
