package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Productos_Precios")
public class ProductoPrecioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="idLocal")
	private LocalEntity local;
	@ManyToOne
	@JoinColumn(name="idProducto")
	private ProductoEntity producto;
	
	private float precio;
	
	public ProductoPrecioEntity() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalEntity getLocal() {
		return local;
	}

	public void setLocal(LocalEntity local) {
		this.local = local;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public float getPrecio() {
		return precio;
	}
	
}
