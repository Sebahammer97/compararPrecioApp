package modelo;

public class Local {

	private int id;
	private Cadena cadena;
	private String direccion;
	private float latitud;
	private float longitud;
	
	public Local(int id, Cadena cadena, String direccion, float latitud, float longitud) {
		super();
		this.id = id;
		this.cadena = cadena;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public Cadena getCadena() {
		return cadena;
	}

	public String getDireccion() {
		return direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}
	
	
	
	/*
	public float getPrecioDeProducto(int id) {
		for(ProductoPrecio p: productos)
		{
			if(p.getId() == id)
			{
				return p.getPrecio();
			}
		}
		return Float.MAX_VALUE;
	}
	
	public boolean tenesProductoById(int idProducto)
	{
		for(ProductoPrecio p: productos)
		{
			if(p.getId() == idProducto)
			{
				return true;
			}
		}
		return false;
	}
	*/
}
