package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public class GenericoDAOImpl <E, R extends CrudRepository<E, Integer>> implements GenericoDAO<E>
{
	
	protected final R repository;   //crear repositorio, con constructor para poder usar el objeto
	
	public GenericoDAOImpl(R repository) 
	{
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> buscarporId(Integer Id) {
		
		return repository.findById(Id);
	}

	@Override
	public E guardar(E entidad) {
		
		return repository.save(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> buscarTodos() {
		
		return repository.findAll();
	}

	@Override
	public void eliminarPorId(Integer Id) {
		repository.deleteById(Id);
	}

}
