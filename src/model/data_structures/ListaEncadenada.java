package model.data_structures;

public class ListaEncadenada<T>
{

	private Nodo<T> nodo;
	private int numeroElementos;
	private Nodo<T> actual;


	
	
	
	public ListaEncadenada()
	{
		nodo = null;	
		numeroElementos=0;
		actual = null;
	}

	
	public void agregar(Nodo<T> elemento)
	{

		if(nodo==null)
		{
			nodo = elemento;
			numeroElementos++;
		}
		else
		{
			actual =  nodo;

			//llega hasta el final de la lista 
			while(actual.darSiguiente()!=null)
			{
				actual = actual.darSiguiente();
			}

			actual.cambiarSiguiente(elemento);
			numeroElementos++;
		}	


	}

	public void borrarElemento(Nodo<T> elemento)
	{
		Nodo<T> siguiente = nodo.darSiguiente();
		actual = nodo;


		//Cuando solo hay un elemento en la lista y coincide con el que se va a eliminar
		if(nodo.darSiguiente()==null)
		{
			if(nodo.compareTo(elemento)==0)
			{
				nodo = null;
				numeroElementos--;
				return;
			}

			return;

		}

		//Más de un elemento en la lista
		else
		{
			//Cuando se borra el primero
			if(nodo.compareTo(elemento)==0)
			{
				nodo = nodo.darSiguiente();
				numeroElementos--;
				return;
			}

			//Más allá del primero 
			while(siguiente!=null)
			{
				if(siguiente.compareTo(elemento)==0)
				{
					actual.cambiarSiguiente(siguiente.darSiguiente());
					numeroElementos--;
					return;
				}			
				actual= siguiente;
				siguiente = siguiente.darSiguiente();

			}


		}
	}


	public int consultarTamaño()
	{
		return numeroElementos;
	}

	
	
	public T consultar(int i)
	{

		int contador = 0;
		actual = nodo;
		if(i>=0 && i<=numeroElementos)
		{
			//sin elementos en la lista
			if(nodo==null)
				return null;

			//Más de un elemento
			while(actual!=null)
			{
				if(contador==i)
					return actual.retornarItem();

				actual = actual.darSiguiente();
				contador++;
			}
		}
		
		return null;

	}

	/**
	 *Para utilizarlo solamente en los tests
	 */
	public Nodo<T> consultarNodoPosicion(int i)
	{

		int contador = 0;
		actual = nodo;
		if(i>=0 && i<=numeroElementos)
		{
			//sin elementos en la lista
			if(nodo==null)
				return null;

			//Más de un elemento
			while(actual!=null)
			{
				if(contador==i)
					return actual;

				actual = actual.darSiguiente();
				contador++;
			}
		}
		
		return null;

	}
	
	public T consultarElementoActual()
	{

		return actual.retornarItem();
		
	}
	
	public Nodo<T> darActual()
	{
		return actual;
	}
	
	public T ultimoElemento()
	{
		actual = nodo;
		while(actual.darSiguiente()!=null)
		{
			actual = actual.darSiguiente();
		}
		
		return actual.retornarItem();
		
	}
	
	public Nodo<T> darUltimo()
	{
		actual = nodo;
		while(actual.darSiguiente()!=null)
		{
			actual = actual.darSiguiente();
		}
		
		return actual;
	}
	

	public void iniciarRecorrido()
	{
		
		actual = nodo;
	}

	public void avanzarNodo()
	{

		if(actual.darSiguiente()==null)
		{
			actual = null;
		}
		else
		{
			actual = actual.darSiguiente();
		}
	}
	
	public void retrocederNodo()
	{
		
		Nodo<T> este = nodo.darSiguiente();
		Nodo<T> esteAnterior = nodo;
		 	
		//Si actual es el primerNodo
		if(actual.compareTo(nodo)==0)
		{
			actual = null;
			return;
		}
				
		//Recorre hasta la última posición
		while(este!=null)
		{
			if(este.compareTo(actual)==0)
			{
				actual = esteAnterior;
				return;
			}
			esteAnterior = este;
			este = este.darSiguiente();
		}
		
		
	}
	
	public T darPrimerElemento()
	{
		return nodo.retornarItem();
		
	}
	
	public Nodo<T> darPrimero()
	{
		return nodo;
	}


}













