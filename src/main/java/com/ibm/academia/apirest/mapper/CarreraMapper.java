package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;

public class CarreraMapper 
{
	public static CarreraDTO mapCarrera(Carrera carrera)    //metodo
	{
		CarreraDTO carreraDTO = new CarreraDTO();    //instanciar objeto CarreraDTO
		
		carreraDTO.setId(carrera.getId());
		carreraDTO.setNombre(carrera.getNombre());
		carreraDTO.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraDTO.setCantidadAniosEstimados(carrera.getCantidadAniosEstimados());
		return carreraDTO;
	}
}
