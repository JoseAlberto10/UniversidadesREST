package com.ibm.academia.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Integer>
{
	//@Query("select c from Carrera c where c.CantidadAniosEstimados = ?1")
	public Iterable<Carrera> findCarrerasByCantidadAniosEstimados(Integer cantidadAniosEstimados);
	
	//@Query("select c from Carrera c where c.nombre like %?1%")
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	
	//@Query("select c from Carrera c where upper(c.nombre) like upper(%?1%)")
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
	
	//@Query("select c from Carrera c where c.CantidadAniosEstimados > ?1")
	public Iterable<Carrera> findCarrerasByCantidadAniosEstimadosAfter(Integer cantidadAniosEstimados);

	
    @Query("select c from Carrera c join fetch c.profesores p where p.nombre = ?1 and p.apellido = ?2")
 	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
