package exceptions;

public class UsuarioException extends Exception {
	
	private static final long serialVersionUID = 9018648492202155948L;

	public UsuarioException(String mensaje) {
		super(mensaje);
	}
}
