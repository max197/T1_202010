package model.logic;

import java.io.Serializable;
import java.util.List;


public class Comparendo implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String fechaHora;
	
	private String medio;
	
	private String claseVehiculo;
	
	private String tipoServicio;
	
	private String codigoInfraccion;
	
	private String descripcion;
	
	private String localidad;
	
	private List<Double> cordenadas;
	
	//Reportar el caso especial en que no exista el comparendo.
	

	public Comparendo(int pId, String pFechaHora,String pMedio , String pClaseVehiculo, String pTipoServicio, String pCodigoInfraccion, String pDescripcion, String pLocalidad, List<Double> pCordenadas)
	{
		
		id = pId;
		
		fechaHora = pFechaHora;
		
		medio = pMedio;
		
		claseVehiculo = pClaseVehiculo;
		
		tipoServicio = pTipoServicio;
		
		codigoInfraccion = pCodigoInfraccion;
		
		descripcion = pDescripcion;
		
		localidad = pLocalidad;
		
		cordenadas = pCordenadas;
				
				
	}
	
	public int darID()
	{
		return id;
	}
	
	public String darFechaHora()
	{
		return fechaHora;
	}
	
	public String darCodigoInfraccion()
	{
		return codigoInfraccion;
	}
	
	public String darClaseVehiculo()
	{
		return claseVehiculo;
	}
	
	public String darTipoServicio()
	{
		return tipoServicio;
	}
	
	public String darLocalidad()
	{
		return localidad;
	}
	
	public String darDescripcion()
	{
		return descripcion;
	}
	
	public List<Double> darCordenadas()
	{
		return cordenadas;
	}

	public String darMedio()
	{
		return medio;
	}

}
