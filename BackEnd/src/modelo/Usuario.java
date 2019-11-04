package modelo;

public class Usuario
{
	private int id;
	private String nombreUsuario;
	private String pass;
	private String email;
	
	public Usuario(int id, String nombreUsuario, String pass, String email) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getPass() {
		return pass;
	}

	public String getEmail() {
		return email;
	}
}