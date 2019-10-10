package modelo;

import java.util.ArrayList;
import java.util.List;

public class Local {

	private int id;
	private String nombre;
	private List<ProductoPrecio> productos = new ArrayList<ProductoPrecio>();
	private float distancia;
	
	public Local(int id, String nombre, List<ProductoPrecio> productos, float distancia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
		this.distancia = distancia;
	}

	public float getDistancia() {
		return distancia;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<ProductoPrecio> getProductos() {
		return productos;
	}
	
	public float getPrecioDeProducto(int id) {
		for(ProductoPrecio p: productos)
		{
			if(p.getId() == id)
			{
				return p.getPrecio();
			}
		}
		return -1;
	}

}
