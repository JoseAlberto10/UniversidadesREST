package com.ibm.academia.apirest.models.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO
{
	private Integer id;
	
	@NotNull(message = "No debe ser Nulo")
	@NotEmpty(message = "No debe ser Vacio")
	private String nombre;
	
	@Positive(message = "El valor debe ser mayor a Cero")
	private Integer cantidadMaterias;
	
	@Positive(message = "El valor debe ser mayor a Cero")
	private Integer cantidadAniosEstimados;

}
