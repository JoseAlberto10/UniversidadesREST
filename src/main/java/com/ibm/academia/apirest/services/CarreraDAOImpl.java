package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.CarreraRepository;

@Service           
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera, CarreraRepository> implements CarreraDAO     //Implementacion de la lógica
{
	@Autowired      
	public CarreraDAOImpl(CarreraRepository repository)
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByCantidadAniosEstimados(Integer cantidadAniosEstimados) {
		
		return repository.findCarrerasByCantidadAniosEstimados(cantidadAniosEstimados);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre) {
		
		return repository.findCarrerasByNombreContains(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
		
		return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByCantidadAniosEstimadosAfter(Integer cantidadAniosEstimados) {
		
		return repository.findCarrerasByCantidadAniosEstimadosAfter(cantidadAniosEstimados);
	}

	@Override
	@Transactional
	public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera)
	{
		Carrera carreraActualizada = null;
		carreraEncontrada.setCantidadAniosEstimados(carrera.getCantidadAniosEstimados());
		carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraActualizada = repository.save(carreraEncontrada);
		return carreraActualizada;
	}

	@Override
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) 
	{
		return repository.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
	}

}
