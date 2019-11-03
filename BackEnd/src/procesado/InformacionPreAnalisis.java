package procesado;

import modelo.Lista;

public class InformacionPreAnalisis
{
	private String nombre;
	private String descripcion;
	private float latitud;
	private float longitud;
	private float maxDistancia;
	private Lista lista;
	
	public InformacionPreAnalisis(String nombre, String descripcion, float latitud, float longitud, float maxDistancia, Lista lista) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.maxDistancia = maxDistancia;
		this.lista = lista;
	}

	public float getMaxDistancia() {
		return maxDistancia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public Lista getLista() {
		return lista;
	}
	
}
