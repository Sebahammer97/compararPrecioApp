package test;

import java.util.ArrayList;

import controlador.Controlador;
import modelo.CompraDecision;
import modelo.ItemLista;
import modelo.ItemResultado;
import modelo.Lista;
import modelo.ProcesadorDeListas;
import modelo.Producto;

public class Test {

	public static void main(String[] args) {
		Lista lc = new Lista();
		for(int i = 1; i <5; i++)
		{
			lc.addItemListaCompra((new ItemLista(new Producto(i,""+i+""), 1)));	
		}
		
		ItemResultado resultado = Controlador.getInstancia().procesarListaCompra(lc);
		
		System.out.println("-------------------------------------------------------");	
		System.out.println("Mejor Ruta:\t"+resultado.getLocal().getNombre()+"\t"+resultado.getPresupuesto()+"\t"+resultado.getLocal().getDistancia()+"");
	
		System.out.println("-------------------------------------------------------");	
		
		ArrayList<CompraDecision> test1 = ProcesadorDeListas.getInstancia().procesarListaMejorPrecioMonoLocal(lc, Controlador.getInstancia().obtenerLocales());
		for(CompraDecision i: test1)
		{
			System.out.println(i.contarDecision());
		}
		
		System.out.println("-------------------------------------------------------");	
		
		ArrayList<CompraDecision> test2 = ProcesadorDeListas.getInstancia().procesarListaMejorPrecioOptimo(lc, Controlador.getInstancia().obtenerLocales());
		for(CompraDecision i: test2)
		{
			System.out.println(i.contarDecision());
		}
		
	}

}
