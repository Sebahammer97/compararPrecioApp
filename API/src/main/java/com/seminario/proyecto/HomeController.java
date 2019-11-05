package com.seminario.proyecto;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.UsuarioException;
import modelo.Imagen;
import procesado.Geodatos;
import procesado.InformacionPreAnalisis;
import procesado.ResultadoPosAnalisis;
import views.CategoriaView;
import views.LocalView;
import views.ProductoView;
import views.UsuarioView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/VerProductos", method = RequestMethod.GET)
	@ResponseBody
	public String verProductos() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<ProductoView> productos = Controlador.getInstancia().obtenerProductos();

		return mapper.writeValueAsString(productos);
	}

	@RequestMapping(value = "/VerProductosDeCategoria", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String verProductosDeCategoria(@RequestBody String categoriaJson) throws JsonParseException, JsonMappingException, IOException{

		//		int id, String titulo, String descripcion

		ObjectMapper mapper = new ObjectMapper();

		ArrayList<ProductoView> productos = Controlador.getInstancia().obtenerProductosByCategoria(mapper.readValue(categoriaJson, CategoriaView.class));

		return mapper.writeValueAsString(productos);
	}

	@RequestMapping(value = "/VerCategorias", method = RequestMethod.GET)
	@ResponseBody
	public String verCategorias() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<CategoriaView> categorias = Controlador.getInstancia().obtenerCategorias();

		return mapper.writeValueAsString(categorias);
	}
	
	@RequestMapping(value = "/VerLocalesEnRango", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String altaReclamo(@RequestBody String geoJson) throws JsonParseException, JsonMappingException, IOException{

		//		float latitud, float longitud, float maxDistancia

		ObjectMapper mapper = new ObjectMapper();
		Geodatos geo = mapper.readValue(geoJson, Geodatos.class);

		ArrayList<LocalView> locales = Controlador.getInstancia().obtenerLocalesEnRango(geo.getLatitud(), geo.getLatitud(), geo.getLongitud());

		return mapper.writeValueAsString(locales);
	}

	@RequestMapping(value = "/ProcesarLista", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String procesarLista(@RequestBody String infoJson) throws JsonParseException, JsonMappingException, IOException{

		//		String nombre, String descripcion, float latitud, float longitud, float maxDistancia, Lista lista

		ObjectMapper mapper = new ObjectMapper();

		ResultadoPosAnalisis resultado = Controlador.getInstancia().procesarListaCompraH(mapper.readValue(infoJson, InformacionPreAnalisis.class));

		return mapper.writeValueAsString(resultado);
	}

	@RequestMapping(value = "/GuardarImagen", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public void guardarImagen(@RequestBody String imagenJson) {

		//		int id, byte[] imagen, String tipo, int idProducto

		ObjectMapper mapper = new ObjectMapper();
		try {
			imagenJson = imagenJson.substring(10, imagenJson.length()-1);
			Imagen i = mapper.readValue(imagenJson, Imagen.class);
			try {
				Controlador.getInstancia().guardarImagen(i);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/CrearUsuario", method = RequestMethod.POST)
	@ResponseBody
	public String altaUsuario(@RequestParam(value="usuario", required=true) String usuario, @RequestParam(value="password", required=true) String password, @RequestParam(value="email", required=true) String email) throws UsuarioException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			//WARNING
			return mapper.writeValueAsString(Controlador.getInstancia().crearUsuario(new UsuarioView(0, email, password, email, 0, "", "")));
			//WARNING
			
		} catch (Exception e) {
			throw new UsuarioException("No se pudo registrar el Usuario");
		}
	}

	@RequestMapping(value = "/AutenticarUsuario", method = RequestMethod.POST)
	@ResponseBody
	public String autenticacionUsuario(@RequestParam(value="email", required=true) String email, @RequestParam(value="password", required=true) String password) throws UsuarioException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Controlador.getInstancia().autorizarUsuario(email, password));
		} catch (Exception e) {
			throw new UsuarioException("No se pudo autenticar el Usuario");
		}
	}
}