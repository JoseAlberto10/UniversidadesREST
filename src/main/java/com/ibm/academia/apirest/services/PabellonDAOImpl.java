package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;


@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO
{
	@Autowired
	public PabellonDAOImpl(PabellonRepository repository)
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findPabellonesByDireccionLocalidad(String localidad)
	{
		return repository.findPabellonesByDireccionLocalidad(localidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findPabellonesByNombre(String nombre)
	{
		return repository.findPabellonesByNombre(nombre);
	}
}