package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Items_Lista")
public class ItemListaEntity {
	
	@Id
	private Integer id;
	@ManyToOne
	@JoinColumn(name="idLista")
	private ListaEntity lista;
	@ManyToOne
	@JoinColumn(name="idProducto_Precio")
	private ProductoPrecioEntity productoPrecio;
	
	private int cantidad;
	
	public ItemListaEntity () {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ListaEntity getLista() {
		return lista;
	}

	public void setLista(ListaEntity lista) {
		this.lista = lista;
	}

	public ProductoPrecioEntity getProductoPrecio() {
		return productoPrecio;
	}

	public void setProductoPrecio(ProductoPrecioEntity productoPrecio) {
		this.productoPrecio = productoPrecio;
	}

	public int getCantidad() {
		return cantidad;
	}

}
