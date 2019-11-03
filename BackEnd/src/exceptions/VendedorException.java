package exceptions;

public class VendedorException extends Exception {
	
	private static final long serialVersionUID = 9018648492202155948L;

	public VendedorException(String mensaje) {
		super(mensaje);
	}
}
