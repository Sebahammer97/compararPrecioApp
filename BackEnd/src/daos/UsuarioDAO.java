package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioEntity;
import exceptions.CategoriaException;
import exceptions.UsuarioException;
import hibernate.HibernateUtil;
import modelo.Usuario;

public class UsuarioDAO
{
	
	private static UsuarioDAO instancia;
	
	private UsuarioDAO() {}
	
	public static UsuarioDAO getInstancia()
	{
		if(instancia==null)
			instancia = new UsuarioDAO();
		return instancia;
	}

	public Usuario getUsuario(Integer id) throws UsuarioException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.id = ?")
			.setInteger(0, id)
			.uniqueResult();
		if(usuario != null)
			return toNegocio(usuario);
		return null;
	}

	public Usuario getUsuarioByEmail(String email) throws UsuarioException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.email = ?")
			.setString(0, email)
			.uniqueResult();
		if(usuario != null)
			return toNegocio(usuario);
		return null;
	}
	
	public ArrayList<Usuario> getUsuarios() throws UsuarioException
	{
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuario = s.createQuery("from UsuarioEntity").list();
		for(UsuarioEntity u : usuario)
			resultado.add(toNegocio(u));
		return resultado;
	}
	
	public void saveUsuario(Usuario u) throws UsuarioException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			UsuarioEntity usuario = new UsuarioEntity(u.getId(), u.getEmail(), u.getPass(), u.getNombre(), u.getEdad(), u.getUbicacion(), u.getDescripcion());
			s.save(usuario);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new UsuarioException("Usuario Error -Fallo al guardar-");
			}
	}
	
	public Usuario toNegocio(UsuarioEntity u) throws UsuarioException
	{
		try {
			return new Usuario(u.getId(), u.getEmail(), u.getPass(), u.getNombre(), u.getEdad(), u.getUbicacion(), u.getDescripcion());
			
		} catch (Exception e) {
			throw new UsuarioException("Usuario Error -Fallo al transformar "+u.getId()+" a Negocio-");
		}
	}
	
	public UsuarioEntity toEntity(Usuario u) throws CategoriaException
	{
		try {
			return new UsuarioEntity(u.getId(), u.getEmail(), u.getPass(), u.getNombre(), u.getEdad(), u.getUbicacion(), u.getDescripcion());
			
		} catch (Exception e) {
			throw new CategoriaException("Usuario Error -Fallo al transformar "+u.getId()+" a Entidad-");
		}
	}
}