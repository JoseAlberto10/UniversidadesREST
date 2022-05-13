package com.ibm.academia.apirest.datos;

import java.math.BigDecimal;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

public class DatosDummy 
{
	public static Carrera carrera01()    //creacion de objeto Carrera 
	{
		return new Carrera(null,"Ingenieria en sistemas",50,5);
	}
	
	public static Carrera carrera02()
	{
		return new Carrera(null,"Licenciatura en sistemas",45,4);
	}
	
	public static Carrera carrera03()
	{
		return new Carrera(null,"Ingenieria Industrial",60,5);
	}
	
	
	
	public static Persona empleado01()
	{
		return new Empleado(null, "Lautaro", "Lopez", "25174036", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona empleado02()
	{
		return new Empleado(null, "Leandro", "Lopez", "25174630", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}

	public static Persona profesor01()
	{
		return new Profesor(null, "Martin", "Lugone", "33908461", new Direccion(), new BigDecimal("60000.00"));
	}
	
	public static Persona alumno01()
	{
		return new Alumno(null, "Jhon", "Benitez", "66001718", new Direccion());
	}
	
	public static Persona alumno02()
	{
		return new Alumno(null, "Andres", "Benitez", "33301718", new Direccion());
	}
	
	public static Persona alumno03()
	{
		return new Alumno(null, "Joaquin", "Leon", "33777718", new Direccion());
	}

}
