package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

public interface AulaDAO extends GenericoDAO<Aula>
{
	public Iterable<Aula> buscarPorTipoPizarron(Pizarron pizarron);
 	public Iterable<Aula> buscarPorNombrePabellon(String nombre);
	public Aula buscarPorNumeroAula(Integer numeroAula);
	public Aula actualizar(Aula aulaEncontrada, Aula aula);
}
