package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Comparendos");
			System.out.println("2. Consultar información del comparendo");
			System.out.println("3. Exit");

		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		

}
