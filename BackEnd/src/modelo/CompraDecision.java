package modelo;

public class CompraDecision
{
	private Producto productoAComprar;
	private int cantidad;
	private Local localDecidido;
	private float precioPrevisto;
	
	public CompraDecision(Producto producto, int cantidad, Local local, float precio)
	{
		this.productoAComprar = producto;
		this.cantidad = cantidad;
		this.localDecidido = local;
		this.precioPrevisto = precio;
	}

	public Producto getProductoAComprar() {
		return productoAComprar;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Local getLocalDecidido() {
		return localDecidido;
	}

	public float getPrecioPrevisto() {
		return precioPrevisto;
	}
	
	public String contarDecision()
	{
		return("Puede comprar "+productoAComprar.getNombre()+" ("+cantidad+") en "+localDecidido.getNombre()+" a un precio de "+precioPrevisto);
	}
	
}