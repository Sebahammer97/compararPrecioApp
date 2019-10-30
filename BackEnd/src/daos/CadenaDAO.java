package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CadenaEntity;
import exceptions.CadenaException;
import exceptions.ListaException;
import hibernate.HibernateUtil;
import modelo.Cadena;

public class CadenaDAO
{
	
	private static CadenaDAO instancia;
	
	private CadenaDAO() {}
	
	public static CadenaDAO getInstancia()
	{
		if(instancia==null)
			instancia = new CadenaDAO();
		return instancia;
	}
	
	public Cadena getCadena(Integer id) throws CadenaException
	{
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

	public ArrayList<Cadena> getCadenas() throws CadenaException
	{
		ArrayList<Cadena> resultado = new ArrayList<Cadena>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<CadenaEntity> cadenas = s.createQuery("from CadenaEntity").list();
		for(CadenaEntity c : cadenas)
			resultado.add(toNegocio(c));
		return resultado;
	}
	
	public void saveCadena(Cadena c) throws ListaException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			CadenaEntity cadena = new CadenaEntity(c.getId(), c.getNombre(), c.getRazonSocial(), c.getCuit());
			s.save(cadena);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new ListaException("Cadena Error -Fallo al guardar-");
			}
	}
	
	public Cadena toNegocio(CadenaEntity c) throws CadenaException
	{
		try {
			return new Cadena(c.getId(), c.getNombre(), c.getRazonSocial(), c.getCuit());
			
		} catch (Exception e) {
			throw new CadenaException("Cadena Error -Fallo al transformar "+c.getId()+" a Negocio-");
		}
	}
	
	public CadenaEntity toEntity(Cadena c) throws CadenaException
	{
		try {
			return new CadenaEntity(c.getId(), c.getNombre(), c.getRazonSocial(), c.getCuit());
			
		} catch (Exception e) {
			throw new CadenaException("Cadena Error -Fallo al transformar "+c.getId()+" a Entidad-");
		}
	}
}
