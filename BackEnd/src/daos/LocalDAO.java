package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.LocalEntity;
import exceptions.LocalException;
import hibernate.HibernateUtil;
import modelo.Cadena;
import modelo.Local;

public class LocalDAO {
	
	private static LocalDAO instancia;
	
	private LocalDAO() {}
	
	public static LocalDAO getInstancia() {
		if(instancia==null)
			instancia = new LocalDAO();
		return instancia;
	}

	public List<Local> getLocales() throws LocalException {
		List<Local> resultado = new ArrayList<Local>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LocalEntity> locales = s.createQuery("from LocalEntity").list();
		for(LocalEntity l : locales)
			resultado.add(toNegocio(l));
		return null;
	}

	public Local toNegocio(LocalEntity l) throws LocalException {
		try {
			if(l != null) {
				Cadena cadena = CadenaDAO.getInstancia().toNegocio(l.getCadena());
				if(cadena != null) {
					return new Local(l.getId(), cadena, l.getDireccion(), l.getLatitud(), l.getLongitud());
				}
			}
		} catch (Exception e) {
			throw new LocalException("No se pudo recuperar Local");
		}
		return null;
	}
}
