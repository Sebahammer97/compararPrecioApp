package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ConsumidorEntity;
import exceptions.ConsumidorException;
import hibernate.HibernateUtil;
import modelo.Consumidor;
import modelo.Usuario;

public class ConsumidorDAO
{
	
	private static ConsumidorDAO instancia;
	
	private ConsumidorDAO() {}
	
	public static ConsumidorDAO getInstancia()
	{
		if(instancia==null)
			instancia = new ConsumidorDAO();
		return instancia;
	}

	public Consumidor getConsumidor(Integer id) throws ConsumidorException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ConsumidorEntity consumidor = (ConsumidorEntity) s.createQuery("from ConsumidorEntity c where c.id = ?")
			.setInteger(0, id)
			.uniqueResult();
		if(consumidor != null)
			return toNegocio(consumidor);
		return null;
	}

	public Consumidor getConsumidorByUsuario(Usuario u) throws ConsumidorException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ConsumidorEntity consumidor = (ConsumidorEntity) s.createQuery("from ConsumidorEntity c where c.usuario.id = ?")
			.setInteger(0, u.getId())
			.uniqueResult();
		if(consumidor != null)
			return toNegocio(consumidor);
		return null;
	}
	
	public void saveConsumidor(Consumidor v) throws ConsumidorException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			ConsumidorEntity consumidor = new ConsumidorEntity(v.getId(), UsuarioDAO.getInstancia().toEntity(v.getUsuario()));
			s.save(consumidor);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new ConsumidorException("Consumidor Error -Fallo al guardar-");
			}
	}
	
	public Consumidor toNegocio(ConsumidorEntity c) throws ConsumidorException
	{
		try {
			return new Consumidor(c.getId(), UsuarioDAO.getInstancia().toNegocio(c.getUsuario()));
			
		} catch (Exception e) {
			throw new ConsumidorException("Consumidor Error -Fallo al transformar "+c.getId()+" a Negocio-");
		}
	}
}