package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.VendedorEntity;
import exceptions.VendedorException;
import hibernate.HibernateUtil;
import modelo.Usuario;
import modelo.Vendedor;

public class VendedorDAO
{
	
	private static VendedorDAO instancia;
	
	private VendedorDAO() {}
	
	public static VendedorDAO getInstancia()
	{
		if(instancia==null)
			instancia = new VendedorDAO();
		return instancia;
	}

	public Vendedor getVendedor(Integer id) throws VendedorException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		VendedorEntity vendedor = (VendedorEntity) s.createQuery("from VendedorEntity v where v.id = ?")
			.setInteger(0, id)
			.uniqueResult();
		if(vendedor != null)
			return toNegocio(vendedor);
		return null;
	}

	public Vendedor getVendedorByUsuario(Usuario u) throws VendedorException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		VendedorEntity vendedor = (VendedorEntity) s.createQuery("from VendedorEntity v where v.usuario.id = ?")
			.setInteger(0, u.getId())
			.uniqueResult();
		if(vendedor != null)
			return toNegocio(vendedor);
		return null;
	}
	
	public void saveVendedor(Vendedor v) throws VendedorException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			VendedorEntity vendedor = new VendedorEntity(v.getId(), UsuarioDAO.getInstancia().toEntity(v.getUsuario()));
			s.save(vendedor);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new VendedorException("Vendedor Error -Fallo al guardar-");
			}
	}
	
	public Vendedor toNegocio(VendedorEntity v) throws VendedorException
	{
		try {
			return new Vendedor(v.getId(), UsuarioDAO.getInstancia().toNegocio(v.getUsuario()));
			
		} catch (Exception e) {
			throw new VendedorException("Vendedor Error -Fallo al transformar "+v.getId()+" a Negocio-");
		}
	}
}