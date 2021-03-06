package procesado;

import java.util.ArrayList;
import java.util.List;

import daos.ListaDAO;
import exceptions.ListaException;
import modelo.Lista;
import modelo.Local;
import modelo.Producto;

public class ProcesadorDeListas
{
	private static ProcesadorDeListas instancia;

	private ProcesadorDeListas()
	{}

	public static ProcesadorDeListas getInstancia()
	{
		if (instancia == null)
			instancia = new ProcesadorDeListas();
		return instancia;
	}

	public ArrayList<CompraDecision> procesarLista_PrecioDistancia_MonoLocal(final Lista listaCompra, final ArrayList<Local> locales, final float latitudActual, final float longitudActual)
	{
		//----------||Analisis de los Locales||-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
		
				ArrayList<Local> localesRevisados = this.analizarLocalesRecibidos(listaCompra, locales);

		//----------||Recolecci�n de datos||--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

		int cantidadLocales = localesRevisados.size();
		int cantidadProductos = listaCompra.getLista().size();

		//Debo o no preguntar al local 'i' en locales.get('i')
		boolean [] localesPreguntar = this.crearArregloBooleanoVerdadero(cantidadLocales);

		//Debo o no preguntar por el producto 'i' en listaCompra.getLista().get('i')
		boolean [] productosPreguntar = this.crearArregloBooleanoVerdadero(cantidadProductos);

		boolean [][] matrizTengo = this.crearMatrizTengo(listaCompra, localesRevisados, cantidadLocales, cantidadProductos, localesPreguntar, productosPreguntar);

		int [] cuantosProductosTieneLocal = this.contarCuantosProductosVendeLocalX(cantidadLocales, cantidadProductos, matrizTengo);
		localesPreguntar = this.verificarPorContador(localesPreguntar, cuantosProductosTieneLocal);

		int [] cuantosLocalesTienenProducto = this.contarCuantosLocalesVendenProductoX(cantidadLocales, cantidadProductos, matrizTengo);
		productosPreguntar = this.verificarPorContador(productosPreguntar, cuantosLocalesTienenProducto);

		//----------||Procesado de datos||----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

		ArrayList<CompraDecision> decisiones = new ArrayList<CompraDecision>();

		float [] presupuestoPorLocal = new float[cantidadLocales];

		int i,j;

		float masBajo = Float.MAX_VALUE;
		for(i = 0; i < cantidadLocales; i++)
		{
			if(localesPreguntar[i] && cuantosProductosTieneLocal[i] == cantidadProductos)
			{
				Local l = locales.get(i);
				float presupuesto = 0;
				for(j = 0; j < cantidadProductos; j++)
				{
					if(productosPreguntar[j] && matrizTengo[i][j])
					{
						Producto p = listaCompra.getLista().get(j).getProducto();
						int c = listaCompra.getLista().get(j).getCantidad();
						presupuesto = presupuesto + l.getPrecioDeProducto(p.getId())*c;
					}
				}
				presupuestoPorLocal[i] = presupuesto;
				if(presupuesto < masBajo)
				{
					masBajo = presupuesto;
				}
			}
		}
		
		ArrayList<Local> localesPreDecision = new ArrayList<Local>();
		
		for(i = 0; i < presupuestoPorLocal.length; i++)
		{
			if(localesPreguntar[i] && presupuestoPorLocal[i] == masBajo)
			{
				localesPreDecision.add(localesRevisados.get(i));
			}
		}

		int mejorLocal = 0;
		float mejorDistancia = Float.MAX_VALUE;
		for(Local l: localesPreDecision)
		{
			if( (Math.abs(latitudActual - l.getLatitud() ) + Math.abs(longitudActual - l.getLongitud() ) ) <= mejorDistancia)
			{
				mejorDistancia= (Math.abs(latitudActual - l.getLatitud() ) + Math.abs(longitudActual - l.getLongitud() ) );
				mejorLocal = l.getId();
			}
		}
			
		for(Local l: localesPreDecision)
		{
			if(l.getId() == mejorLocal)
			{
				for(j = 0; j < cantidadProductos; j++)
				{
					if(productosPreguntar[j])
					{
						Producto p = listaCompra.getLista().get(j).getProducto();
						int c = listaCompra.getLista().get(j).getCantidad();
						decisiones.add(new CompraDecision(p, c, l, l.getPrecioDeProducto(p.getId())));
						productosPreguntar[j] = false;
					}
				}
				break;
			}
		}
		
		/*
		for(i = 0; i < presupuestoPorLocal.length; i++)
		{
			if(localesPreguntar[i] && presupuestoPorLocal[i] == masBajo)
			{
				for(j = 0; j < cantidadProductos; j++)
				{
					if(productosPreguntar[j])
					{
						Producto p = listaCompra.getLista().get(j).getProducto();
						int c = listaCompra.getLista().get(j).getCantidad();
						Local l = locales.get(i);
						decisiones.add(new CompraDecision(p, c, l, l.getPrecioDeProducto(p.getId())));
						localesPreguntar[i] = false;
						productosPreguntar[j] = false;
					}
				}
				break;
			}
		}
		*/
		
		this.guardarLista(listaCompra);
		return decisiones;
	}

	public ArrayList<CompraDecision> procesarLista_PrecioDistancia_MultiLocal(final Lista listaCompra, final ArrayList<Local> locales, final float latitudActual, final float longitudActual)
	{
		//----------||Analisis de los Locales||-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
		
		ArrayList<Local> localesRevisados = this.analizarLocalesRecibidos(listaCompra, locales);

		//----------||Recolecci�n de datos||--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
		
		int cantidadLocales = localesRevisados.size();
		int cantidadProductos = listaCompra.getLista().size();
		
		//Debo o no preguntar al local 'i' en localesRevisados.get('i')
		boolean [] localesPreguntar = this.crearArregloBooleanoVerdadero(cantidadLocales); 

		//Debo o no preguntar por el producto 'i' en listaCompra.getLista().get('i')
		boolean [] productosPreguntar = this.crearArregloBooleanoVerdadero(cantidadProductos);

		boolean [][] matrizTengo = this.crearMatrizTengo(listaCompra, localesRevisados, cantidadLocales, cantidadProductos, localesPreguntar, productosPreguntar);

		int [] cuantosProductosTieneLocal = this.contarCuantosProductosVendeLocalX(cantidadLocales, cantidadProductos, matrizTengo);
		localesPreguntar = this.verificarPorContador(localesPreguntar, cuantosProductosTieneLocal);

		int [] cuantosLocalesTienenProducto = this.contarCuantosLocalesVendenProductoX(cantidadLocales, cantidadProductos, matrizTengo);
		productosPreguntar = this.verificarPorContador(productosPreguntar, cuantosLocalesTienenProducto);

		boolean [][] matrizMejor = this.crearMatrizMejor(listaCompra, localesRevisados, cantidadLocales, cantidadProductos, localesPreguntar, productosPreguntar); 

		//----------||Procesado de datos||----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

		ArrayList<CompraDecision> decisiones = new ArrayList<CompraDecision>();

		int cantidadRevisada = 0;
		while(cantidadRevisada != cantidadProductos)
		{
			//Creo un arreglo de enteros para consultar cuantos "mejores" tienen el local 'i'
			int [] cuantosMejorTieneLocal = new int [cantidadLocales]; 
			int mayorCantidad = 0;
			for(int i = 0; i < cantidadLocales;i++)
			{
				if(localesPreguntar[i])
				{
					int contador = 0;
					for(int j = 0; j < cantidadProductos; j++)
					{
						if(productosPreguntar[j])
						{
							if(matrizMejor[i][j])
							{
								contador++;
							}
						}
					}
					cuantosMejorTieneLocal[i] = contador;
					if(cuantosMejorTieneLocal[i] > mayorCantidad)
					{
						mayorCantidad= cuantosMejorTieneLocal[i];
					}
				}
			}
			//
			for(int i = 0; i < cantidadLocales;i++)
			{
				if(localesPreguntar[i])
				{
					if(cuantosMejorTieneLocal[i] == mayorCantidad)
					{
						for(int j = 0; j < cantidadProductos; j++)
						{
							if(productosPreguntar[j])
							{
								if(matrizMejor[i][j])
								{
									Producto p = listaCompra.getLista().get(j).getProducto();
									int c = listaCompra.getLista().get(j).getCantidad();
									Local l = localesRevisados.get(i);
									decisiones.add(new CompraDecision(p, c, l, l.getPrecioDeProducto(p.getId())));
									localesPreguntar[i] = false;
									productosPreguntar[j] = false;
								}
							}
						}
						cantidadRevisada = cantidadRevisada + mayorCantidad;
						break;
					}
				}
			}
		}
		this.guardarLista(listaCompra);
		return decisiones;
	}

	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
	
	//Analizo los locales obtenidos viendo si merece la pena considerarlo en el analisis, es decir, si vende algo de la lista se queda.
	private ArrayList<Local> analizarLocalesRecibidos(Lista listaCompra, ArrayList<Local> locales) {
		
		int cantidadLocales = locales.size();
		int cantidadProductos = listaCompra.getLista().size();
		ArrayList<Local> resultado = new ArrayList<Local>();
		
		int [] cuantosProductosTieneLocalA = this.contarCuantosProductosVendeLocalX
				(cantidadLocales, cantidadProductos,
				this.crearMatrizTengo(listaCompra, locales, cantidadLocales, cantidadProductos, this.crearArregloBooleanoVerdadero(cantidadLocales), this.crearArregloBooleanoVerdadero(cantidadProductos))
				);
		
		for(int i = 0; i<cuantosProductosTieneLocalA.length; i++)
		{
			if(cuantosProductosTieneLocalA[i] > 0)
			{
				resultado.add(locales.get(i));
			}
		}
		
		return resultado;
	}

	//Creo un arreglo booleano para consultar
	private boolean [] crearArregloBooleanoVerdadero(int tama�o)
	{
		boolean [] arreglo = new boolean[tama�o];
		int i;
		for(i = 0; i < tama�o; i++)
		{
			arreglo[i] = true;
		}
		return arreglo;
	}

	//Creo una matriz booleana para consultar si el local 'i' tiene el producto 'j' (matrizTengo[i][j])
	private boolean[][] crearMatrizTengo(Lista listaCompra, List<Local> locales, int cantidadLocales, int cantidadProductos, boolean [] localesPreguntar, boolean [] productosPreguntar)
	{
		boolean [][] matrizTengo = new boolean [cantidadLocales][cantidadProductos];
		int i,j;
		for(i = 0; i < cantidadLocales; i++)
		{
			for(j = 0; j < cantidadProductos; j++)
			{
				matrizTengo[i][j] = false;
			}
		}
		//Pregunto si el local 'i' tiene al producto 'j' fila a fila
		for(i = 0; i < cantidadLocales; i++)
		{
			if(localesPreguntar[i])
			{
				for(j = 0; j < cantidadProductos; j++)
				{
					if(productosPreguntar[j])
					{
						matrizTengo[i][j] = locales.get(i).tenesProductoById(listaCompra.getLista().get(j).getProducto().getId());
					}
				}
			}
		}
		return matrizTengo;
	}

	//Creo una matriz booleana para consultar si el local 'i' tiene el mejor precio para el producto 'j' (matrizMejor[i][j])
	private boolean[][] crearMatrizMejor(Lista listaCompra, List<Local> locales, int cantidadLocales, int cantidadProductos, boolean [] localesPreguntar, boolean [] productosPreguntar)
	{
		boolean [][] matrizMejor = new boolean [cantidadLocales][cantidadProductos];
		int i,j;
		for(i = 0; i < cantidadLocales; i++)
		{
			for(j = 0; j < cantidadProductos; j++)
			{
				matrizMejor[i][j] = false;
			}
		}

		//Busco el mejor local para comprar el producto 'j'
		for(j = 0; j < cantidadProductos; j++)
		{
			float [] preciosPorLocal = new float[cantidadLocales];
			float mejorPrecio = Float.MAX_VALUE;
			if(productosPreguntar[j])
			{
				for(i = 0; i < cantidadLocales; i++)
				{
					if(localesPreguntar[i])
					{
						float precio = locales.get(i).getPrecioDeProducto(listaCompra.getLista().get(j).getProducto().getId());
						preciosPorLocal[i] = precio;
						if(precio < mejorPrecio)
						{
							mejorPrecio = precio;
						}
					}
				}			
				for(i = 0; i < cantidadLocales; i++)
				{
					if(preciosPorLocal[i]==mejorPrecio)
					{
						matrizMejor[i][j] = true;
					}
				}
			}
		}
		return matrizMejor;
	}

	//Creo un arreglo de enteros para consultar cuantos productos de la lista tienen el local 'i'
	private int[] contarCuantosProductosVendeLocalX (int cantidadLocales, int cantidadProductos, boolean[][] matrizTengo)
	{
		int [] resultado = new int [cantidadLocales]; 
		for(int i = 0; i < cantidadLocales;i++)
		{
			int contador = 0;
			for(int j = 0; j < cantidadProductos; j++)
			{
				if(matrizTengo[i][j])
				{
					contador++;
				}
			}
			resultado[i] = contador;
		}
		return resultado;
	}

	//Creo un arreglo de enteros para consultar cuantos locales tienen el producto 'j' de la lista
	private int[] contarCuantosLocalesVendenProductoX (int cantidadLocales, int cantidadProductos, boolean[][] matrizTengo)
	{
		int [] resultado = new int [cantidadProductos]; 
		for(int j = 0; j < cantidadProductos; j++)
		{
			int contador = 0;
			for(int i = 0; i < cantidadLocales;i++)
			{
				if(matrizTengo[i][j])
				{
					contador++;
				}
			}
			resultado[j] = contador;
		}
		return resultado;
	}

	//Si arregloVerificador[i] es cero, arregloBooleano[i] se cancela
	private boolean[] verificarPorContador(boolean[] arregloBooleano, int[] arregloVerificador)
	{
		for(int i = 0; i < arregloBooleano.length; i++)
		{
			if(arregloVerificador[i] == 0)
			{
				arregloBooleano[i] = false; //<----- Implica que el local no es relevante
			}
		}
		return arregloBooleano;
	}

	private void guardarLista(Lista listaCompra)
	{
		try {
			ListaDAO.getInstancia().saveList(listaCompra);
			
		} catch (ListaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
