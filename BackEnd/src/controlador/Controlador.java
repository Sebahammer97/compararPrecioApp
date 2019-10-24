package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.ItemLista;
import modelo.ItemResultado;
import modelo.Lista;
import modelo.Local;
import modelo.ProductoPrecio;

public class Controlador {
	
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
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
	
	public List<Local> obtenerLocales() {
		List<ProductoPrecio> productos1 = new ArrayList<ProductoPrecio>();
		List<ProductoPrecio> productos2 = new ArrayList<ProductoPrecio>();
		List<ProductoPrecio> productos3 = new ArrayList<ProductoPrecio>();
		List<ProductoPrecio> productos4 = new ArrayList<ProductoPrecio>();
		List<ProductoPrecio> productos5 = new ArrayList<ProductoPrecio>();
		
		List<Local> locales = new ArrayList<Local>();
		
		Random x = new Random();
		
		for(int i = 1; i <5; i++)
		{
			productos1.add(new ProductoPrecio(i, x.nextInt(50)+10));	
		}
		Local l = new Local(1, "Coto", productos1, 3.5f);
		locales.add(l);
		
		for(int i = 1; i <5; i++)
		{
			productos2.add(new ProductoPrecio(i, x.nextInt(50)+10));	
		}
		l = new Local(2, "Disco", productos2, 2.5f);
		locales.add(l);
		
		for(int i = 1; i <5; i++)
		{
			productos3.add(new ProductoPrecio(i, x.nextInt(50)+10));	
		}
		l = new Local(3, "Carrefour", productos3, 1.0f);
		locales.add(l);
		
		for(int i = 1; i <5; i++)
		{
			productos4.add(new ProductoPrecio(i, x.nextInt(50)+10));	
		}
		l = new Local(4, "Walt-Mart", productos4, 8.5f);
		locales.add(l);
		
		for(int i = 1; i <5; i++)
		{
			productos5.add(new ProductoPrecio(i, x.nextInt(50)+10));	
		}
		l = new Local(5, "Norte", productos5, 1.0f);
		locales.add(l);
		
		return locales;
	}
}
