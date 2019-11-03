package controlador;

import java.util.ArrayList;

import daos.CategoriaDAO;
import daos.LocalDAO;
import daos.ProductoDAO;
import daos.UsuarioDAO;
import exceptions.CategoriaException;
import exceptions.LocalException;
import exceptions.ProductoException;
import exceptions.UsuarioException;
import modelo.Categoria;
import modelo.Lista;
import modelo.Local;
import modelo.Producto;
import modelo.Usuario;
import procesado.InformacionPreAnalisis;
import procesado.ProcesadorDeListas;
import procesado.ResultadoPosAnalisis;

public class Controlador {
	
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
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
	
	public ArrayList<Categoria> obtenerCategorias()
	{
		try {
			return CategoriaDAO.getInstancia().getCategorias();
		} catch (CategoriaException e){
			e.printStackTrace();
		}
		return new ArrayList<Categoria>();
	}
	
	public ArrayList<Local> obtenerLocales()
	{
		try {
			return LocalDAO.getInstancia().getLocales();
		} catch (LocalException e){
			e.printStackTrace();
		}
		return new ArrayList<Local>();
	}
	
	public ArrayList<Local> obtenerLocalesEnRango(final float latitudActual, final float longitudActual, final float maxDistancia)
	{
		try {
			return LocalDAO.getInstancia().getLocalesEnRango(latitudActual, longitudActual, maxDistancia);
		} catch (LocalException e){
			e.printStackTrace();
		}
		return new ArrayList<Local>();
	}
	
	public boolean crearUsuario(Usuario u)
	{
		try {
			Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByNombre(u.getNombreUsuario());
			if(usuario == null)
			{
				UsuarioDAO.getInstancia().saveUsuario(u);
				return true;
			}
			return false;
			
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Usuario autorizarUsuario(String nombreUsuario, String pass)
	{
		try {
			Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByNombre(nombreUsuario);
			if(usuario != null)
			{
				 if(usuario.getPass() == pass)
				 {
				 return usuario;
				 }
			}
			return usuario;
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Producto> obtenerProductosByCategoria(Categoria c)
	{
		try {
			return ProductoDAO.getInstancia().getProductosByCategoria(c);
		} catch (ProductoException e){
			e.printStackTrace();
		}
		return new ArrayList<Producto>();
	}
	
	public ArrayList<Local> obtenerLocalesIntegrosEnRango(final float latitudActual, final float longitudActual, final float maxDistancia)
	{
		try {
			ArrayList<Local> locales = LocalDAO.getInstancia().getLocalesEnRango(latitudActual, longitudActual, maxDistancia);
			for(Local l: locales)
			{
				l.cargarListadoDePrecios();
			}
			return locales;
		} catch (LocalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Local>();		
	}
	
	public ResultadoPosAnalisis procesarListaCompra(InformacionPreAnalisis info, String prioridad, String opcion)
	{	
		ArrayList<Local> locales = obtenerLocalesIntegrosEnRango(info.getLatitud(), info.getLongitud(), info.getMaxDistancia());
		if(locales.size() == 0)
		{
			return new ResultadoPosAnalisis();
		}
		Lista lista = info.getLista();
		float latitudActual = info.getLatitud();
		float longitudActual = info.getLongitud();
		
		switch(prioridad)
		{
		case "Precio_Distancia":
			switch(opcion)
			{
			case "Mono_Local":
				return new ResultadoPosAnalisis(ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MonoLocal(lista, locales, latitudActual, longitudActual));
				
			case "Multi_Local":
				return new ResultadoPosAnalisis(ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MultiLocal(lista, locales, latitudActual, longitudActual));
			}
			break;
		}
		return new ResultadoPosAnalisis(null);
	}
	
	public ResultadoPosAnalisis procesarListaCompraH(InformacionPreAnalisis info)
	{	
		ArrayList<Local> locales = obtenerLocalesIntegrosEnRango(info.getLatitud(), info.getLongitud(), info.getMaxDistancia());
		Lista lista = info.getLista();
		float latitudActual = info.getLatitud();
		float longitudActual = info.getLongitud();
		
		//HARDCOREADO <-----
		String prioridad = "Precio_Distancia";
		String opcion = "Mono_Local";
		//HARDCOREADO <-----
		
		switch(prioridad)
		{
		case "Precio_Distancia":
			switch(opcion)
			{
			case "Mono_Local":
				return new ResultadoPosAnalisis(ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MonoLocal(lista, locales, latitudActual, longitudActual));
				
			case "Multi_Local":
				return new ResultadoPosAnalisis(ProcesadorDeListas.getInstancia().procesarLista_PrecioDistancia_MultiLocal(lista, locales, latitudActual, longitudActual));
			}
			break;
		}
		return new ResultadoPosAnalisis(null);
	}
}
