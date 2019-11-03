package modelo;

public class Imagen {
	
	private int id;
	private byte[] imagen;
	private String tipo;
	private int idProducto;
	
	public Imagen(int id, byte[] imagen, String tipo, int idProducto) {
		this.id = id;
		this.imagen = imagen;
		this.tipo = tipo;
		this.idProducto = idProducto;
	}
	
	public Imagen(int id, String imagen, String tipo, int idProducto) {
		this.id = id;
		this.imagen = imagen.getBytes();
		this.tipo = tipo;
		this.idProducto = idProducto;
	}
	
	public Imagen() {}

	public int getId() {
		return id;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getIdReclamo() {
		return idProducto;
	}
}

