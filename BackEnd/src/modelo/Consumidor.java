package modelo;

public class Consumidor
{
	private int id;
	private Usuario usuario;
	
	public Consumidor(int id, Usuario usuario)
	{
		this.id = id;
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
}
