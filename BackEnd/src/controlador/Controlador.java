package controlador;

import java.util.ArrayList;

import daos.CategoriaDAO;
import daos.ImagenDAO;
import daos.LocalDAO;
import daos.ProductoDAO;
import daos.UsuarioDAO;
import entities.ProductoEntity;
import exceptions.CategoriaException;
import exceptions.ImagenException;
import exceptions.LocalException;
import exceptions.ProductoException;
import exceptions.UsuarioException;
import modelo.Categoria;
import modelo.Imagen;
import modelo.Lista;
import modelo.Local;
import modelo.Producto;
import modelo.Usuario;
import procesado.InformacionPreAnalisis;
import procesado.ProcesadorDeListas;
import procesado.ResultadoPosAnalisis;
import views.CategoriaView;
import views.LocalView;
import views.ProductoView;
import views.UsuarioView;

public class Controlador {

	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public ArrayList<ProductoView> obtenerProductos()
	{
		ArrayList<ProductoView> resultado = new ArrayList<ProductoView>();
		try {
			for(Producto p: ProductoDAO.getInstancia().getProductos())
			{
				resultado.add(new ProductoView(p));
			}
			return resultado;
		} catch (ProductoException e){
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<CategoriaView> obtenerCategorias()
	{
		ArrayList<CategoriaView> resultado = new ArrayList<CategoriaView>();
		try {
			for(Categoria c: CategoriaDAO.getInstancia().getCategorias())
			{
				resultado.add(new CategoriaView(c));
			}
			return resultado;
		} catch (CategoriaException e){
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<LocalView> obtenerLocales()
	{
		ArrayList<LocalView> resultado = new ArrayList<LocalView>();
		try {
			for(Local l: LocalDAO.getInstancia().getLocales())
			{
				resultado.add(new LocalView(l));
			}
			return resultado;
		} catch (LocalException e){
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<LocalView> obtenerLocalesEnRango(final float latitudActual, final float longitudActual, final float maxDistancia)
	{
		ArrayList<LocalView> resultado = new ArrayList<LocalView>();
		try {
			for(Local l: LocalDAO.getInstancia().getLocalesEnRango(latitudActual, longitudActual, maxDistancia))
			{
				resultado.add(new LocalView(l));
			}
			return resultado;
		} catch (LocalException e){
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearUsuario(UsuarioView uv)
	{
		try {
			Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByEmail(uv.getEmail());
			if(usuario == null)
			{
				UsuarioDAO.getInstancia().saveUsuario(new Usuario(uv.getId(),uv.getEmail(), uv.getPass(), uv.getNombre(), uv.getEdad(), uv.getUbicacion(), uv.getDescripcion()));
				return true;
			}
			return false;
			
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public UsuarioView autorizarUsuario(String email, String pass)
	{
		try {
			Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByEmail(email);
			if(usuario != null)
			{
				if(usuario.getPass() == pass)
				{
					return new UsuarioView(usuario.getId(), usuario.getEmail(), usuario.getNombre(), usuario.getEdad(), usuario.getUbicacion(), usuario.getDescripcion());
				}
			}
			return null;
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ProductoView> obtenerProductosByCategoria(int id) throws CategoriaException
	{
		Categoria c = CategoriaDAO.getInstancia().getCategoria(id);
		ArrayList<ProductoView> resultado = new ArrayList<ProductoView>();
		try {
			for(Producto p: ProductoDAO.getInstancia().getProductosByCategoria(c))
			{
				resultado.add(new ProductoView(p));
			}
			return resultado;
		} catch (ProductoException e){
			e.printStackTrace();
		}
		return resultado;
	}

	//WARNING
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
	//WARNING
	
	public void guardarImagen(Imagen i) throws ImagenException, ProductoException
	{
		ProductoEntity producto = ProductoDAO.getInstancia().getProductoEntity(i.getIdProducto());
		ImagenDAO.getInstancia().saveImagen(producto, i);
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
