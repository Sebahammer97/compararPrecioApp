package views;

import java.util.ArrayList;

import modelo.Cadena;
import modelo.Local;

public class LocalView {
	private int id;
	private Cadena cadena;
	private String direccion;
	private float latitud;
	private float longitud;
	private ArrayList<ProductoPrecioView> listado;
	
	public LocalView(int id, Cadena cadena, String direccion, float latitud, float longitud) {
		this.id = id;
		this.cadena = cadena;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.listado = new ArrayList<ProductoPrecioView>();
	}
	
	public LocalView(Local l) {
		this.id = l.getId();
		this.cadena = l.getCadena();
		this.direccion = l.getDireccion();
		this.latitud = l.getLatitud();
		this.longitud = l.getLongitud();
		this.listado = new ArrayList<ProductoPrecioView>();
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
	
	public ArrayList<ProductoPrecioView> getListado() {
		return listado;
	}

}
