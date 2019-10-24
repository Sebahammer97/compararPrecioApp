package modelo;

public class ItemLista {

	private int id;
	private Producto producto;
	private int cantidad;
	
	public ItemLista(int id, Producto producto, int cantidad) {
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public int getId()
	{
		return id;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
}
