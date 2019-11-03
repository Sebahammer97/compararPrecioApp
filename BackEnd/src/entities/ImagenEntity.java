package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="imagenes")
public class ImagenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private byte[] imagen;
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private ProductoEntity producto;
	
	public ImagenEntity() {	}

	public ImagenEntity(byte[] imagen, String tipo, ProductoEntity producto) {
		this.imagen = imagen;
		this.tipo = tipo;
		this.producto = producto;
	}
	
	public ImagenEntity(int id, byte[] imagen, String tipo, ProductoEntity producto) {
		this.id = id;
		this.imagen = imagen;
		this.tipo = tipo;
		this.producto = producto;
	}

	public int getId() {
		return id;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public String getTipo() {
		return tipo;
	}

	public ProductoEntity getProducto() {
		return producto;
	}
	
	
}
