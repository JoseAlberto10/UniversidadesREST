package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

@Embeddable
public class Direccion implements Serializable {

	private String calle;
	private String numero;
	private String codigoPostal;
	private String departamento;
	private String piso;
	private String localidad;
	
	public Direccion() 
	{
		
	}

	public Direccion(String calle, String numero, String codigoPostal, String departamento, String piso,
			String localidad) {
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
		this.piso = piso;
		this.localidad = localidad;
	}

	private static final long serialVersionUID = 8500313989796186076L;
}
