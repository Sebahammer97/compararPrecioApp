package views;

import modelo.Categoria;

public class CategoriaView {
	
	private int id;
	private String titulo;
	private String descripcion;
	
	public CategoriaView(int id, String titulo, String descripcion) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	
	public CategoriaView(Categoria c) {
		this.id = c.getId();
		this.titulo = c.getTitulo();
		this.descripcion = c.getDescripcion();
	}
	
	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
