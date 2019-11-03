package test;

import java.util.ArrayList;

import controlador.Controlador;
import daos.ProductoDAO;
import exceptions.CadenaException;
import exceptions.LocalException;
import exceptions.ProductoException;
import exceptions.ProductoPrecioException;
import modelo.ItemLista;
import modelo.Lista;
import modelo.Producto;
import procesado.InformacionPreAnalisis;
import procesado.ResultadoPosAnalisis;

public class Test {

	public static void main(String[] args) throws LocalException, CadenaException, ProductoPrecioException, ProductoException {

		/*
		for(Local l: LocalDAO.getInstancia().getLocales())
		{	
			System.out.println(""+l.getId()+" | "+l.getCadena().getNombre()+"");
		}

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		for(Producto p: ProductoDAO.getInstancia().getProductos())
		{	
			System.out.println(""+p.getNombre()+"");
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		for(ProductoPrecio pp: ProductoPrecioDAO.getInstancia().getProductoPrecioByLocal(1))
		{	
			System.out.println(""+pp.getId()+" | "+pp.getLocal().getId()+" | "+pp.getProducto().getId()+"");
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		 */
		/*
		for(Local l: LocalDAO.getInstancia().getLocales())
		{	
			System.out.print(l.getListado().size());
			l.cargarListadoDePrecios();
			System.out.println(" | "+l.getListado().size());
			System.out.println("-------------------------------------------------------");

		}
		 */	
		/*
		Lista lc = new Lista();
		for(int i = 1; i <5; i++)
		{
			lc.addItemListaCompra((new ItemLista(new Producto(i,""+i+""), 1)));	
		}

		ItemResultado resultado = Controlador.getInstancia().procesarListaCompra(lc);

		System.out.println("-------------------------------------------------------");	
		System.out.println("Mejor Ruta:\t"+resultado.getLocal().getNombre()+"\t"+resultado.getPresupuesto()+"\t"+resultado.getLocal().getDistancia()+"");

		System.out.println("-------------------------------------------------------");	
*/		
		ArrayList<Producto> productos = ProductoDAO.getInstancia().getProductos();
//		ArrayList<Local> locales = LocalDAO.getInstancia().getLocales();
		
/*
		for(Producto p: productos)
		{
			System.out.println(p.getId()+" | "+p.getNombre());
		}
		
		for(Local l: locales)
		{
			l.cargarListadoDePrecios();
		}
		Controlador.getInstancia().crearUsuario(new Usuario(0, "testeo", "test", "test"));
*/

		ArrayList<ItemLista> listado = new ArrayList<ItemLista>();
		listado.add(new ItemLista(0, productos.get(0), 2));
		listado.add(new ItemLista(0, productos.get(1), 3));
		listado.add(new ItemLista(0, productos.get(2), 4));

		Lista lc = new Lista(0, "010", "testeo", listado);
		
		//https://gis.stackexchange.com/questions/8650/measuring-accuracy-of-latitude-and-longitude <----- Posible explicacion de como mide estos floats geoposicionales
		
		System.out.println("-----0.1-----");
		
		System.out.println("\nMonoLocal:");
		ResultadoPosAnalisis lector1 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.1f,lc), "Precio_Distancia", "Mono_Local");
		System.out.println(lector1.getDecision());
/*
		System.out.println("\nMultiLocal:");
		ResultadoPosAnalisis lector2 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.1f,lc), "Precio_Distancia", "Multi_Local");
		System.out.println(lector2.getDecision());
		*/
		System.out.println("-----0.01-----");
		
		System.out.println("\nMonoLocal:");
		ResultadoPosAnalisis lector3 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.01f,lc), "Precio_Distancia", "Mono_Local");
		System.out.println(lector3.getDecision());
/*
		System.out.println("\nMultiLocal:");
		ResultadoPosAnalisis lector4 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.01f,lc), "Precio_Distancia", "Multi_Local");
		System.out.println(lector4.getDecision());
	*/	
		System.out.println("-----0.001-----");
		
		System.out.println("\nMonoLocal:");
		ResultadoPosAnalisis lector5 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.001f,lc), "Precio_Distancia", "Mono_Local");
		System.out.println(lector5.getDecision());
/*
		System.out.println("\nMultiLocal:");
		ResultadoPosAnalisis lector6 = Controlador.getInstancia().procesarListaCompra(new InformacionPreAnalisis(lc.getNombre(), lc.getDescripcion(), -34.6167f, -58.3817f, 0.001f,lc), "Precio_Distancia", "Multi_Local");
		System.out.println(lector6.getDecision());
*/

	}
}
