package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cadenas")
public class CadenaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private String razon_social;
	
	private String cuit;
	
	public CadenaEntity() {}
	
	public CadenaEntity(Integer id, String nombre, String razon_social, String cuit)
	{
		this.id = id;
		this.nombre = nombre;
		this.razon_social = razon_social;
		this.cuit = cuit;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getRazonSocial() {
		return razon_social;
	}
	public String getCuit() {
		return cuit;
	}

}
