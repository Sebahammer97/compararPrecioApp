package modelo;

public class ProductoPrecio {

	private int id;
	private Local local;
	private Producto producto;
	private float precio;
	
	public ProductoPrecio(int id, Local local, Producto producto, float precio) {
		this.id = id;
		this.local = local;
		this.producto = producto;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public Local getLocal() {
		return local;
	}

	public Producto getProducto() {
		return producto;
	}

	public float getPrecio() {
		return precio;
	}
	
}
