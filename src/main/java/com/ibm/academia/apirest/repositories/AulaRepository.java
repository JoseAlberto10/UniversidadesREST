package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;


@Repository("repositorioAulas")
public interface AulaRepository extends CrudRepository<Aula, Integer>
{

	@Query(value = "select * from universidad.aulas where tipo_pizarron = ?1", nativeQuery = true)
	public Iterable<Aula> buscarPorTipoPizarron(Pizarron pizarron);
	
    @Query("select a from Aula a join fetch a.pabellon p where p.nombre = ?1")
 	public Iterable<Aula> buscarPorNombrePabellon(String nombre);
    
	@Query(value = "select * from universidad.aulas where numero_aula = ?1", nativeQuery = true)
	public Aula buscarPorNumeroAula(Integer numeroAula);

}
