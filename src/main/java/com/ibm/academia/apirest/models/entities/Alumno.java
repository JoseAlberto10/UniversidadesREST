package com.ibm.academia.apirest.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "alumnos", schema = "universidad")
//@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona

{	
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "carrera_id", foreignKey = @ForeignKey (name = "FK_CARRERA_ID"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "alumnos"})
	private Carrera carrera;
	
	public Alumno() {
		super();
	}

	public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) 
	{
		super(id, nombre, apellido, dni, direccion);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Alumno []";
	}

	private static final long serialVersionUID = 282135527639709536L;
}
