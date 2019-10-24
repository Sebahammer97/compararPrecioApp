package modelo;

import java.util.List;

public class Lista {

	private int id;
	private String nombre;
	private String descripcion;
	private List<ItemLista> items;
	
	public Lista(int id, String nombre, String descripcion, List<ItemLista> items) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<ItemLista> getLista() {
		return items;
	}
	
}
