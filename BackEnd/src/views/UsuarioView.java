package views;

import modelo.Usuario;

public class UsuarioView
{
	private int id;
	private String email;
	private String pass;
	private String nombre;
	private int edad;
	private String ubicacion;
	private String descripcion;
	
	public UsuarioView(int id, String email, String pass, String nombre, int edad, String ubicacion, String descripcion) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.nombre = nombre;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}
	
	public UsuarioView(Usuario u) {
		this.id = u.getId();
		this.email = u.getEmail();
		this.pass = u.getPass();
		this.nombre = u.getNombre();
		this.edad = u.getEdad();
		this.ubicacion = u.getUbicacion();
		this.descripcion = u.getDescripcion();	
	}
	
	public UsuarioView(int id, String email, String nombre, int edad, String ubicacion, String descripcion) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.pass = "La curiosidad mato al gato";
	}

	public UsuarioView() { }	
	
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
