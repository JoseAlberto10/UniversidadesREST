package com.ibm.academia.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniversidadesRestApplicationTests 
{
	Calculadora calculadora = new Calculadora();   //crear objeto 

	@Test
	@DisplayName("Test: Suma de Valores")
	void sumarValores()
	{
////////GIVEN   --> Define el contexto o precondiciones para el test
		Integer valorA = 2;
		Integer valorB = 3;
		
////////WHEN   --> Ejecutar la accion a probar 
		Integer expected = calculadora.sumar(valorA, valorB);
		 
////////THEN  --> Validar que lo que se está probando funciona correctamente
		Integer resultadoEsperado = 5;
		assertThat(expected).isEqualTo(resultadoEsperado); 
	}
	
	class Calculadora
	{
		Integer sumar(Integer a, Integer b)
		{
			return a + b;
		}
	}
}
