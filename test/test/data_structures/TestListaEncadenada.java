package test.data_structures;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.logic.Comparendo;
import model.logic.Modelo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;



public class TestListaEncadenada
{

	private ListaEncadenada<Comparendo> lista;
	private Modelo modelo;

	/**
	 * Lista vacia
	 */
	@Before
	public void setUp1() 
	{
		lista =  new ListaEncadenada<>();
	}

	/**
	 * 
	 * Lista con los datos del json
	 */
	public void setUp2() throws IOException
	{
		try{
			modelo  = new Modelo();
			lista = modelo.cargarComparendos();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	/**
	 * Prueba que la lista esté vacía
	 */
	@Test 
	public void testListaEncadenadaVacia() {

		setUp1();
		assertEquals( "La lista debería tener cero elementos", 0,lista.consultarTamaño());

	}


	/**
	 * Prueba que la lista tenga los 20 objetos del json
	 */
	@Test 
	public void testListaEncadenada() throws IOException
	{
		setUp2();
		assertEquals("La lista debería tener 20 elementos", 20, lista.consultarTamaño());

	}

	@Test 
	public void testConsultarPorPosicion() throws IOException
	{
		setUp2();
		/*
		assertTrue("No es el comparendo esperado", lista.consultar(0).equals(lista.darPrimerElemento()));
		 */
		System.out.println(lista.consultar(0).darID());
		assertEquals("No es el esperado", 29042, lista.consultar(0).darID() );

		lista.avanzarNodo();

		assertTrue("No es el comparendo esperado", lista.consultar(1).equals(lista.consultarElementoActual()));

		lista.avanzarNodo();

		assertTrue("No es el comparendo esperado",lista.consultar(2).equals(lista.consultarElementoActual()));

	}

	@Test 
	public void testConsultarElementoActual() throws IOException
	{
		setUp2();
		lista.iniciarRecorrido();
		assertTrue("No es el comparendo esperado", lista.consultarElementoActual().equals(lista.darPrimerElemento()));

	}
	@Test
	public void testAgregar() throws IOException 
	{

		setUp2();
		Nodo nuevo = new Nodo(new Comparendo(1, "a", "b", "c","d" ,"e" , "f","g", new ArrayList()));


		lista.agregar(nuevo);

		assertEquals("No se agrego", 21, lista.consultarTamaño());
		assertTrue("No se agrego correctamente", lista.darActual().darSiguiente().equals(nuevo));


	}
	
	
	@Test
	public void testEliminarElemento() throws IOException 
	{

		setUp2();
		Nodo nuevo = new Nodo(new Comparendo(1, "a", "b", "c","d" ,"e" , "f","g", new ArrayList()));
		lista.agregar(nuevo);
		
		
		lista.borrarElemento(nuevo);
		assertEquals("No se eliminó", 20, lista.consultarTamaño());
		
		
		lista.borrarElemento(lista.darPrimero());
		assertEquals("No se eliminó correctamente", 509329, lista.darPrimerElemento().darID());
		
		lista.borrarElemento(lista.darUltimo());
		assertEquals("No se eliminó correctamente", 224494, lista.ultimoElemento().darID());
		
		
		/**
		 * Se elimina un elemento diferente al primero o al ultimo
		 * El primer elemento tiene ID509329 porque las anteriores llamadas al método borrar eliminó el que tenía ID29042
		 */
		lista.iniciarRecorrido();
		lista.borrarElemento(lista.consultarNodoPosicion(1));
		lista.avanzarNodo();
		assertEquals("No se eliminó correctamente", 176695, lista.consultarElementoActual().darID());
		
	}




}









