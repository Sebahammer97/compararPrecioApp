package modelo;

public class Producto {

	private int id;
	private String nombre;
	private String descripcion;
	private Categoria categoria;
	
	public Producto(int id, String nombre, String descripcion, Categoria categoria) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
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
	
}
