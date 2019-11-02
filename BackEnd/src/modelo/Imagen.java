package modelo;

public class Imagen {
	
	private int numero;
	private byte[] imagen;
	private String tipo;
	private int idProducto;
	
	public Imagen(int numero, byte[] imagen, String tipo, int idProducto) {
		this.numero = numero;
		this.imagen = imagen;
		this.tipo = tipo;
		this.idProducto = idProducto;
	}
	
	public Imagen(int numero, String imagen, String tipo, int idProducto) {
		this.numero = numero;
		this.imagen = imagen.getBytes();
		this.tipo = tipo;
		this.idProducto = idProducto;
	}
	
	public Imagen() {}

	public int getNumero() {
		return numero;
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

