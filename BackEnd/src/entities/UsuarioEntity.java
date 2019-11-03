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
	private Integer id;
	
	private String nombreUsuario;
	private String pass;
	private String email;
	
	public UsuarioEntity() {}
	
	public UsuarioEntity(String nombreUsuario, String pass, String email) {
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
		this.email = email;
	}
	
	public UsuarioEntity(int id, String nombreUsuario, String pass, String email) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
		this.email = email;
	}
	
	public Integer getId() {
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