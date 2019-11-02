package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import exceptions.CadenaException;
import exceptions.CategoriaException;
import hibernate.HibernateUtil;
import modelo.Categoria;

public class CategoriaDAO
{
	
	private static CategoriaDAO instancia;
	
	private CategoriaDAO() {}
	
	public static CategoriaDAO getInstancia()
	{
		if(instancia==null)
			instancia = new CategoriaDAO();
		return instancia;
	}

	public Categoria getCategoria(Integer id) throws CategoriaException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		CategoriaEntity categoria = (CategoriaEntity) s.createQuery("from CategoriaEntity c where c.id = ?")
			.setInteger(0, id)
			.uniqueResult();
		if(categoria != null)
			return toNegocio(categoria);
		return null;
	}

	public ArrayList<Categoria> getCategorias() throws CategoriaException
	{
		ArrayList<Categoria> resultado = new ArrayList<Categoria>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<CategoriaEntity> categorias = s.createQuery("from CategoriaEntity").list();
		for(CategoriaEntity c : categorias)
			resultado.add(toNegocio(c));
		return resultado;
	}
	
	public void saveCategoria(Categoria c) throws CategoriaException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			CategoriaEntity categoria = new CategoriaEntity(c.getId(), c.getTitulo(), c.getDescripcion());
			s.save(categoria);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new CategoriaException("Categoria Error -Fallo al guardar-");
			}
	}
	
	public Categoria toNegocio(CategoriaEntity c) throws CategoriaException
	{
		try {
			return new Categoria(c.getId(), c.getTitulo(), c.getDescripcion());
			
		} catch (Exception e) {
			throw new CategoriaException("Categoria Error -Fallo al transformar "+c.getId()+" a Negocio-");
		}
	}
	
	public CategoriaEntity toEntity(Categoria c) throws CadenaException
	{
		try {
			return new CategoriaEntity(c.getId(), c.getTitulo(), c.getDescripcion());
			
		} catch (Exception e) {
			throw new CadenaException("Categoria Error -Fallo al transformar "+c.getId()+" a Entidad-");
		}
	}
}
