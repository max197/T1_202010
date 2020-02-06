package model.logic;



import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaEncadenada<Comparendo> datos;

	/**
	 * Constructor del modelo del mundo 
	 */
	public Modelo()
	{
		datos = new ListaEncadenada<Comparendo>();
	}


	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.consultarTamaño();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Nodo<Comparendo> dato)
	{	
		datos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public void buscarID(int pID) throws IOException
	{

		try {
			datos = cargarComparendos();
			boolean encontro = false; 
			int tamaño = datos.consultarTamaño();
			int indicador = 1;
			datos.iniciarRecorrido();
			while(indicador<=tamaño && !encontro)
			{

				if(datos.consultarElementoActual().darID()==pID)
				{

					System.out.println(datos.consultarElementoActual().darID());
					System.out.println(datos.consultarElementoActual().darFechaHora());
					System.out.println(datos.consultarElementoActual().darCodigoInfraccion());
					System.out.println(datos.consultarElementoActual().darClaseVehiculo());
					System.out.println(datos.consultarElementoActual().darTipoServicio());
					System.out.println(datos.consultarElementoActual().darLocalidad());

					encontro = true;
				}

				
				datos.avanzarNodo();
				indicador++;

			}

			if(!encontro)
				System.out.println("No existe información del comparendo");
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	public ListaEncadenada<Comparendo> cargarComparendos() throws IOException
	{

		try 
		{
			ListaEncadenada<Comparendo> lista = readJsonStream(new DataInputStream(new FileInputStream("./data/comparendos_dei_2018_small.geojson")));

			return lista;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;


	}


	private ListaEncadenada<Comparendo> readJsonStream(InputStream in) throws IOException
	{
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try {
			return readData(reader);
		} finally {
			reader.close();
		}
	}

	private  ListaEncadenada<Comparendo> readData(JsonReader reader) throws IOException {

		ListaEncadenada<Comparendo> comparendos = new ListaEncadenada<Comparendo>();

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("features") && reader.peek() != JsonToken.NULL) {
				comparendos = readFeaturesArray(reader);
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return comparendos;
	}

	private ListaEncadenada<Comparendo> readFeaturesArray(JsonReader reader) throws IOException {
		ListaEncadenada<Comparendo> comparendos = new ListaEncadenada<Comparendo>();

		reader.beginArray();
		while (reader.hasNext()) {
			comparendos.agregar(readFeature(reader));
		}
		reader.endArray();
		return comparendos;
	}



	private Nodo<Comparendo> readFeature(JsonReader reader) throws IOException 
	{


		int OBJECTID = -1;
		String FECHA_HORA = null;
		String MEDIO_DETE = null;
		String CLASE_VEHI = null;
		String TIPO_SERVI = null;
		String INFRACCION = null;
		String DES_INFRAC = null;
		String LOCALIDAD = null;
		List<Double> coordinates = null;


		reader.beginObject();

		while (reader.hasNext()) 
		{
			String name = reader.nextName();

			if (name.equals("properties"))
			{
				reader.beginObject();
				while (reader.hasNext()) {
					String nombre = reader.nextName();
					if (nombre.equals("OBJECTID")) {
						OBJECTID = reader.nextInt();
					} else if (nombre.equals("FECHA_HORA")) {
						FECHA_HORA = reader.nextString();
					} else if (nombre.equals("MEDIO_DETE")) {
						MEDIO_DETE = reader.nextString();
					} else if (nombre.equals("CLASE_VEHI")) {
						CLASE_VEHI = reader.nextString();
					} else if (nombre.equals("TIPO_SERVI")) {
						TIPO_SERVI = reader.nextString();
					} else if (nombre.equals("INFRACCION")) {
						INFRACCION = reader.nextString();
					} else if (nombre.equals("DES_INFRAC")) {
						DES_INFRAC = reader.nextString();
					} else if (nombre.equals("LOCALIDAD")) {
						LOCALIDAD = reader.nextString();
					} else {
						reader.skipValue();
					}
				}
				reader.endObject();

			}
			else if (name.equals("geometry"))
			{
				reader.beginObject();
				while (reader.hasNext())
				{
					String n = reader.nextName();
					if (n.equals("coordinates") && reader.peek() != JsonToken.NULL)
					{
						coordinates = readDoublesArray(reader);

					} else {
						reader.skipValue();
					}
				}
				reader.endObject();
			}
			else 
				reader.skipValue();

		}


		reader.endObject();
		Comparendo comparendo = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, coordinates);
		return new Nodo<Comparendo>(comparendo);

	}


	private List<Double> readDoublesArray(JsonReader reader) throws IOException
	{
		List<Double> doubles = new ArrayList<Double>();

		reader.beginArray();
		while (reader.hasNext()) {
			doubles.add(reader.nextDouble());
		}
		reader.endArray();
		return doubles;
	}





}
