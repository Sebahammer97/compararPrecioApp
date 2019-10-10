package modelo;

public class ItemResultado {

	private int id;
	private Local local;
	private float presupuesto;


	public ItemResultado(int id, Local local, float presupuesto) {
		super();
		this.id = id;
		this.local = local;
		this.presupuesto = presupuesto;
	}

	public int getId() {
		return id;
	}

	public Local getLocal() {
		return local;
	}

	public float getPresupuesto() {
		return presupuesto;
	}

}
