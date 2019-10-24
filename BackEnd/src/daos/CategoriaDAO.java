package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import exceptions.CategoriaException;
import hibernate.HibernateUtil;
import modelo.Categoria;

public class CategoriaDAO {
	
	private static CategoriaDAO instancia;
	
	private CategoriaDAO() {}
	
	public static CategoriaDAO getInstancia() {
		if(instancia==null)
			instancia = new CategoriaDAO();
		return instancia;
	}

	public Categoria getCategoria(Integer id) throws CategoriaException {
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

	public Categoria toNegocio(CategoriaEntity c) throws CategoriaException {
		try {
			return new Categoria(c.getId(), c.getTitulo(), c.getDescripcion());
		} catch (Exception e) {
			throw new CategoriaException("No se pudo recuperar la Categoria");
		}
	}

}
