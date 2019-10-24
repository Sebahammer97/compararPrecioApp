package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CadenaEntity;
import exceptions.CadenaException;
import hibernate.HibernateUtil;
import modelo.Cadena;

public class CadenaDAO {
	
	private static CadenaDAO instancia;
	
	private CadenaDAO() {}
	
	public static CadenaDAO getInstancia() {
		if(instancia==null)
			instancia = new CadenaDAO();
		return instancia;
	}
	
	public Cadena getCadena(Integer id) throws CadenaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		CadenaEntity cadena = (CadenaEntity) s.createQuery("from CadenaEntity c where c.id = ?")
			.setInteger(0, id)
			.uniqueResult();
		if(cadena != null)
			return toNegocio(cadena);
		return null;
	}

	public Cadena toNegocio(CadenaEntity c) throws CadenaException {
		try {
			return new Cadena(c.getId(), c.getNombre(), c.getRazonSocial(), c.getCuit());
		} catch (Exception e) {
			throw new CadenaException("No se pudo recuperar Cadena");
		}
	}
}
