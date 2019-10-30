package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Locales")
public class LocalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="idCadena")
	private CadenaEntity cadena;
	
	private String direccion;
	private float latitud;
	private float longitud;
	
	public LocalEntity() {}
	
	public LocalEntity(Integer id, CadenaEntity cadena, String direccion, float latitud, float longitud) {
		this.id = id;
		this.cadena = cadena;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CadenaEntity getCadena() {
		return cadena;
	}
	public void setCadena(CadenaEntity cadena) {
		this.cadena = cadena;
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
	
}
