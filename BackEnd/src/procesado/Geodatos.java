package procesado;

public class Geodatos
{
	private float latitud;
	private float longitud;
	private float maxDistancia;
	
	public Geodatos(float latitud, float longitud, float maxDistancia) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.maxDistancia = maxDistancia;
	}
	
	public float getLatitud() {
		return latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public float getMaxDistancia() {
		return maxDistancia;
	}
	
}
