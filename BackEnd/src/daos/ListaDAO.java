package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ListaEntity;
import exceptions.ListaException;
import hibernate.HibernateUtil;
import modelo.ItemLista;
import modelo.Lista;

public class ListaDAO {

	private static ListaDAO instancia;
	
	private ListaDAO() {}
	
	public static ListaDAO getInstancia() {
		if(instancia==null)
			instancia = new ListaDAO();
		return instancia;
	}
	
	public int obtenerUltimoId() throws ListaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		int id = (Integer) s.createQuery("select max(id) from ListaEntity")
			.uniqueResult();
		if(id != 0)
			return id;
		else
			throw new ListaException("No se pudo recuperar el ultimo id");
		
	}
	
	public void saveList(Lista l) throws ListaException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			ListaEntity le = new ListaEntity(l.getNombre(), l.getDescripcion());
			s.save(le);
			s.getTransaction().commit();
			le.setId(obtenerUltimoId());
			
			for(ItemLista i: l.getLista())
			{
				ItemListaDAO.getInstancia().save(i, le);
			}
			} catch (Exception e) {
				throw new ListaException("No se pudo guardar la Lista");
			}
	}
}
