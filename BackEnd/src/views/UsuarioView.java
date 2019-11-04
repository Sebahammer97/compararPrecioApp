package views;

import modelo.Usuario;

public class UsuarioView
{
	private int id;
	private String nombreUsuario;
	private String pass;
	private String email;
	
	public UsuarioView(int id, String nombreUsuario, String pass, String email) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
		this.email = email;
	}
	
	public UsuarioView(Usuario u) {
		this.id = u.getId();
		this.nombreUsuario = u.getNombreUsuario();
		this.pass = u.getPass();
		this.email = u.getEmail();
	}
	
	public UsuarioView(int id, String nombreUsuario, String email) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.pass = "La curiosidad mato al gato";
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
