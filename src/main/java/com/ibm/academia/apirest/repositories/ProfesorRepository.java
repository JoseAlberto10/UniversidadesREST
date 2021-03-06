package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Persona;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository
{
	//@Query("select p from Profesor p where p.carreras.nombre = ?1")

	@Query("select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
	public Iterable<Persona> findProfesoresbyCarrera(String nombre);

}
