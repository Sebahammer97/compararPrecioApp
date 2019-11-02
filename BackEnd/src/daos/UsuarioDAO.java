package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioEntity;
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

	public Usuario getUsuarioByNombre(String nombreUsuario) throws UsuarioException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.nombreUsuario = ?")
			.setString(0, nombreUsuario)
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
			UsuarioEntity categoria = new UsuarioEntity(u.getId(), u.getNombreUsuario(), u.getPass(), u.getEmail());
			s.save(categoria);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new UsuarioException("Usuario Error -Fallo al guardar-");
			}
	}
	
	public Usuario toNegocio(UsuarioEntity u) throws UsuarioException
	{
		try {
			return new Usuario(u.getId(), u.getNombreUsuario(), u.getPass(), u.getEmail());
			
		} catch (Exception e) {
			throw new UsuarioException("Usuario Error -Fallo al transformar "+u.getId()+" a Negocio-");
		}
	}
}