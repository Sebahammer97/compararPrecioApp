package controlador;

import java.util.ArrayList;

import daos.LocalDAO;
import daos.ProductoDAO;
import exceptions.LocalException;
import exceptions.ProductoException;
import modelo.Lista;
import modelo.Local;
import modelo.Producto;
import procesado.CompraDecision;
import procesado.ProcesadorDeListas;

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
	
	public ArrayList<Local> obtenerLocales()
	{
		try {
			return LocalDAO.getInstancia().getLocales();
		} catch (LocalException e){
			e.printStackTrace();
		}
		return new ArrayList<Local>();
	}
	
	public ArrayList<Producto> obtenerProductos()
	{
		try {
			return ProductoDAO.getInstancia().getProductos();
		} catch (ProductoException e){
			e.printStackTrace();
		}
		return new ArrayList<Producto>();
	}

	public ArrayList<Local> obtenerLocalesIntegros()
	{
		ArrayList<Local> locales = this.obtenerLocales();
		for(Local l: locales)
		{
			l.cargarListadoDePrecios();
		}
		return locales;
	}
	
	public ArrayList<Local> obtenerLocalesIntegrosEnRango(final float latitudActual, final float longitudActual, final float maxDistancia)
	{
		ArrayList<Local> aux = this.obtenerLocales();
		ArrayList<Local> locales = new ArrayList<Local>();
		for(Local l: aux)
		{
			/*
			System.out.println("("+latitudActual+" - "+l.getLatitud()+" ) + ("+longitudActual+" - "+l.getLongitud()+" )" );
			System.out.println( (Math.abs(latitudActual - l.getLatitud() ) + Math.abs(longitudActual - l.getLongitud() ) ) );
			*/
			
			if( (Math.abs(latitudActual - l.getLatitud() ) + Math.abs(longitudActual - l.getLongitud() ) ) <= maxDistancia)
			{
				locales.add(l);
			}
		}
		
		for(Local l: locales)
		{
			l.cargarListadoDePrecios();
		}
		return locales;
	}
	
	public ArrayList<CompraDecision> procesarListaCompra(Lista lista, final float latitudActual, final float longitudActual, final float maxDistancia, String prioridad, String opcion)
	{	
		ArrayList<Local> locales = obtenerLocalesIntegrosEnRango(latitudActual, longitudActual, maxDistancia);
		switch(prioridad)
		{
		case "Precio_Distancia":
			switch(opcion)
			{
			case "Mono_Local":
				return ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MonoLocal(lista, locales);
				
			case "Multi_Local":
				return ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MultiLocal(lista, locales);
			}
			break;
		}
		return new ArrayList<CompraDecision>();
	}
	
}
