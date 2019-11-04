package views;

import java.util.List;

import modelo.Categoria;
import modelo.Imagen;
import modelo.Producto;

public class ProductoView {
	
	private int id;
	private String nombre;
	private String descripcion;
	private CategoriaView categoria;
	private List<Imagen> imagenes;
	
	public ProductoView(int id, String nombre, String descripcion, Categoria categoria, List<Imagen> imagenes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = new CategoriaView(categoria);
		this.imagenes = imagenes;
	}
	
	public ProductoView(Producto p) {
		this.id = p.getId();
		this.nombre = p.getNombre();
		this.descripcion = p.getDescripcion();
		this.categoria = new CategoriaView(p.getCategoria());
		this.imagenes = p.getImagenes();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public CategoriaView getCategoria() {
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
