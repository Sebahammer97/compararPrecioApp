package modelo;

public class Usuario
{
	private int id;
	private String email;
	private String pass;
	private String nombre;
	private int edad;
	private String ubicacion;
	private String descripcion;
	
	public Usuario(int id, String email, String pass, String nombre, int edad, String ubicacion, String descripcion) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.nombre = nombre;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}