package controlador;

import java.util.ArrayList;
import java.util.List;

import daos.LocalDAO;
import exceptions.LocalException;
import modelo.Local;

public class Controlador {
	
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	/*
	public ItemResultado procesarListaCompra(Lista compra) {
		//Obtener todos los locales
		List<Local> locales = obtenerLocales();
		ItemResultado resultado = null;
		System.out.println("\tLocal\tPresupuesto\tDistancia\tRelacion");
		for(Local l: locales)
		{
			float presupuesto = 0;
			for(ItemLista i: compra.getLista())
			{
				presupuesto += l.getPrecioDeProducto(i.getProducto().getId());
			}
			if(resultado==null)
			{
				resultado = new ItemResultado(1,l,presupuesto);
				System.out.println("Ruta:\t"+l.getNombre()+"\t"+presupuesto+"\t"+l.getDistancia()+"\t"+presupuesto/l.getDistancia()+"");
			}
			else{			
				//Miro Precio, sino Relacion Precio-Distancia
				if(presupuesto <= resultado.getPresupuesto())
				{
					if(presupuesto < resultado.getPresupuesto())
					{
						resultado = new ItemResultado(1,l,presupuesto);
					}
					else if(presupuesto/l.getDistancia() < resultado.getPresupuesto()/resultado.getLocal().getDistancia())
					{
						resultado = new ItemResultado(1,l,presupuesto);
					}

				}
				System.out.println("Ruta:\t"+l.getNombre()+"\t"+presupuesto+"\t"+l.getDistancia()+"\t"+presupuesto/l.getDistancia()+"");

			}
			
		}
		return resultado;
	}
	*/
	
	public List<Local> obtenerLocales()
	{
		try {
			return LocalDAO.getInstancia().getLocales();
		} catch (LocalException e){
			
			e.printStackTrace();
		}
		return new ArrayList<Local>();
	}
}
