package modelo;

import java.util.List;

public class Producto {

	private int id;
	private String nombre;
	private String descripcion;
	private Categoria categoria;
	private List<Imagen> imagenes;
	
	public Producto(int id, String nombre, String descripcion, Categoria categoria, List<Imagen> imagenes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.imagenes = imagenes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}
	
}