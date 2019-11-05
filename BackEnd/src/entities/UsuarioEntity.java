package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;
	private String pass;
	private String nombre;
	private int edad;
	private String ubicacion;
	private String descripcion;

	public UsuarioEntity() {}

	public UsuarioEntity(int id, String email, String pass, String nombre, int edad, String ubicacion, String descripcion) {
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.nombre = nombre;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}

	public UsuarioEntity(String email, String pass, String nombre, int edad, String ubicacion, String descripcion) {
		this.email = email;
		this.pass = pass;
		this.nombre = nombre;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}

	public Integer getId() {
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