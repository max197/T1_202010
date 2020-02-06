package controller;

import java.util.Scanner;


import model.data_structures.ListaEncadenada;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}


	public void run() throws Exception 
	{

		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		System.out.println("Hola");



		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("--------- \nCargar comparendos de la ciudad de Bogotá para el periodo 2018 ");
				ListaEncadenada<Comparendo> lista = modelo.cargarComparendos();
				System.out.println("Total comparendos: " + lista.consultarTamaño());
				System.out.println("Primer comparendo: " + lista.darPrimerElemento().darID());
				System.out.println("Ultimo comparendo: " + lista.ultimoElemento().darID());
				break;
			case 2: 
				view.printMessage("--------- \nConsultar información de un comparendo");
				ListaEncadenada<Comparendo> l = modelo.cargarComparendos();
				view.printMessage("--------- \nDigite ID del comparendo ");
				int id = lector.nextInt();
				modelo.buscarID(id);
				break;
			case 3: 
				view.printMessage("--------- \nMenu cerrado");
				fin = true;
				break;







			}


		}
	}
}


