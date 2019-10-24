package modelo;

public class Cadena {
	
	private int id;
	private String nombre;
	private String razonSocial;
	private String cuit;
	
	public Cadena(int id, String nombre, String razonSocial, String cuit) {
		this.id = id;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getCuit() {
		return cuit;
	}
	
}
