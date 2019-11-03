package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Consumidores")
public class ConsumidorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="idUsuario")
	private UsuarioEntity usuario;
	
	public ConsumidorEntity() {}
	
	public ConsumidorEntity(UsuarioEntity usuario)
	{
		this.usuario = usuario;
	}

	public ConsumidorEntity(int id, UsuarioEntity usuario)
	{
		this.id = id;
		this.usuario = usuario;
	}
	
	public Integer getId() {
		return id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}
}