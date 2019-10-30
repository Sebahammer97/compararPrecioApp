package modelo;

import java.util.ArrayList;

import daos.ProductoPrecioDAO;
import exceptions.ProductoPrecioException;

public class Local {

	private int id;
	private Cadena cadena;
	private String direccion;
	private float latitud;
	private float longitud;
	private ArrayList<ProductoPrecio> listado;
	
	public Local(int id, Cadena cadena, String direccion, float latitud, float longitud) {
		super();
		this.id = id;
		this.cadena = cadena;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		listado = new ArrayList<ProductoPrecio>();
	}

	public int getId() {
		return id;
	}

	public Cadena getCadena() {
		return cadena;
	}

	public String getDireccion() {
		return direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}
	
	public ArrayList<ProductoPrecio> getListado()
	{
		return listado;
	}
	
	public void cargarListadoDePrecios()
	{
		try {
			this.listado = ProductoPrecioDAO.getInstancia().getProductoPrecioByLocal(this.id);
		} catch (ProductoPrecioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public float getPrecioDeProducto(int id) {
		for(ProductoPrecio p: listado)
		{
			if(p.getProducto().getId() == id)
			{
				return p.getPrecio();
			}
		}
		return Float.MAX_VALUE;
	}
	
	public boolean tenesProductoById(int idProducto)
	{
		for(ProductoPrecio p: listado)
		{
			if(p.getProducto().getId() == idProducto)
			{
				return true;
			}
		}
		return false;
	}
	
	public String presentarLocal()
	{
		return ("Local: "+cadena.getNombre()+" | Direccion: "+direccion+"\n");
	}
}
