package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Items_Lista")
public class ItemListaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="idLista")
	private ListaEntity lista;
	
	@ManyToOne
	@JoinColumn(name="idProducto")
	private ProductoEntity producto;
	
	private int cantidad;
	
	public ItemListaEntity(){
		super();
	}
	
	public ItemListaEntity (ListaEntity lista, ProductoEntity producto, int cantidad) {
		this.lista = lista;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public ItemListaEntity (int id, ListaEntity lista, ProductoEntity producto, int cantidad) {
		this.id = id;
		this.lista = lista;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
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

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

}
