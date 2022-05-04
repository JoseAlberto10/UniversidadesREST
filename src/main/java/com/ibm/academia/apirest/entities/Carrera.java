package com.ibm.academia.apirest.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;
	
	@Column(name = "cantidad_materias")
	private Integer cantidadMaterias;
	
	@Column(name = "cantidad_anios")
	private Integer cantidadAniosEstimados;
	
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
	private Set<Alumno> alumnos;
	
	@ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
	private Set<Profesor> profesores;
	
	public Carrera(Integer id, String nombre, Integer cantidadMaterias, Integer cantidadAniosEstimados) {
		
		this.id = id;
		this.nombre = nombre;
		this.cantidadMaterias = cantidadMaterias;
		this.cantidadAniosEstimados = cantidadAniosEstimados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	
	@PrePersist
	public void antesPersistir() 
	{
		this.fechaAlta = new Date();
	}

	@PreUpdate
	public void antesActualizar() 
	{
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 7386711690996525949L;
}
