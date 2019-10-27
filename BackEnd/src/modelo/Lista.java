package modelo;

import java.util.ArrayList;

public class Lista {

	private int id;
	private String nombre;
	private String descripcion;
	private ArrayList<ItemLista> items;
	
	public Lista(int id, String nombre, String descripcion, ArrayList<ItemLista> items) {
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

	public ArrayList<ItemLista> getLista() {
		return items;
	}
	
}
