package modelo;

public class Categoria {
	
	private int id;
	private String titulo;
	private String descripcion;
	
	public Categoria(int id, String titulo, String descripcion) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
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
