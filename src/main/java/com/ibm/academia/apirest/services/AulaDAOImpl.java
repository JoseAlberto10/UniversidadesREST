package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO
{
	
	@Autowired 
	public AulaDAOImpl(AulaRepository repository) 
	{
		super(repository);	
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> buscarPorTipoPizarron(Pizarron pizarron) 
	{
		return repository.buscarPorTipoPizarron(pizarron);
	}

	@Override
	public Iterable<Aula> buscarPorNombrePabellon(String nombre)
	{

		return repository.buscarPorNombrePabellon(nombre);
	}

	@Override
	public Aula buscarPorNumeroAula(Integer numeroAula)
	{
		return repository.buscarPorNumeroAula(numeroAula);
	}

	@Override
	public Aula actualizar(Aula aulaEncontrada, Aula aula)
	{
        Aula aulaActualizada = null;
        aulaEncontrada.setNumeroAula(aula.getNumeroAula());
        aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());
        aulaEncontrada.setPizarron(aula.getPizarron());
        aulaActualizada = repository.save(aulaEncontrada);
        return aulaActualizada;
	}
}
