package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Productos")
public class ProductoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private CategoriaEntity categoria;
	
	private String nombre;
	private String descripcion;
	
	public ProductoEntity(){
		super();
	}
	
	public ProductoEntity(CategoriaEntity categoria, String nombre, String descripcion) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public ProductoEntity(int id, CategoriaEntity categoria, String nombre, String descripcion) {
		this.id = id;
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
