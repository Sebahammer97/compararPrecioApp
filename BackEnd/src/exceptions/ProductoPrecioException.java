package exceptions;

public class ProductoPrecioException extends Exception {
	
	private static final long serialVersionUID = 9018648492209155948L;

	public ProductoPrecioException(String mensaje) {
		super(mensaje);
	}
}
