package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.LocalEntity;
import exceptions.ListaException;
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

	public Local getLocal(Integer id) throws LocalException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		LocalEntity local = (LocalEntity) s.createQuery("from LocalEntity l where l.id = ?")
		.setInteger(0, id)
		.uniqueResult();
		if(local != null)
			return toNegocio(local);
		return null;
	}
	
	public ArrayList<Local> getLocales() throws LocalException {
		ArrayList<Local> resultado = new ArrayList<Local>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LocalEntity> locales = s.createQuery("from LocalEntity").list();
		for(LocalEntity l : locales)
			resultado.add(toNegocio(l));
		return resultado;
	}
	
	public ArrayList<Local> getLocalesEnRango(float latitudActual, float longitudActual, float maxDistancia) throws LocalException {
		ArrayList<Local> resultado = new ArrayList<Local>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		float limiteInferiorLatitud = latitudActual - maxDistancia;
		float limiteSuperiorLatitud = latitudActual + maxDistancia;
		float limiteInferiorLongitud = longitudActual - maxDistancia;
		float limiteSuperiorLongitud = longitudActual + maxDistancia;
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<LocalEntity> locales = s.createQuery("from LocalEntity l where (l.latitud between ? and ?) and (l.longitud between ? and ?)")
		.setFloat(0, limiteInferiorLatitud)
		.setFloat(1, limiteSuperiorLatitud)
		.setFloat(2, limiteInferiorLongitud)
		.setFloat(3, limiteSuperiorLongitud)
		.list();
		for(LocalEntity l : locales)
			resultado.add(toNegocio(l));
		return resultado;
	}

	public void saveLocal(Local l) throws LocalException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			LocalEntity local = new LocalEntity(l.getId(), CadenaDAO.getInstancia().toEntity(l.getCadena()), l.getDireccion(), l.getLatitud(), l.getLongitud());
			s.save(local);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new LocalException("Local Error -Fallo al guardar-");
			}
	}
	
	public Local toNegocio(LocalEntity l) throws LocalException
	{
		try {
			if(l != null) {
				Cadena cadena = CadenaDAO.getInstancia().toNegocio(l.getCadena());
				if(cadena != null) {
					return new Local(l.getId(), cadena, l.getDireccion(), l.getLatitud(), l.getLongitud());
				}
			}
			
		} catch (Exception e) {
			throw new LocalException("Local Error -Fallo al recuperar "+l.getId()+" a Negocio-");
		}
		return null;
	}
}
