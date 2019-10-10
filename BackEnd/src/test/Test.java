package test;

import controlador.Controlador;
import modelo.ItemListaCompra;
import modelo.ItemResultado;
import modelo.ListaCompra;
import modelo.Producto;

public class Test {

	public static void main(String[] args) {
		ListaCompra lc = new ListaCompra();
		for(int i = 1; i <6; i++)
		{
			lc.addItemListaCompra((new ItemListaCompra(new Producto(i,""+i+""), 1)));	
		}
		
		ItemResultado resultado = Controlador.getInstancia().procesarListaCompra(lc);
		System.out.println("-------------------------------------------------------");	
		System.out.println("Mejor Ruta:\t"+resultado.getLocal().getNombre()+"\t"+resultado.getPresupuesto()+"\t"+resultado.getLocal().getDistancia()+"");
	}

}
