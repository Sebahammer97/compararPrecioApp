package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.CadenaEntity;
import entities.CategoriaEntity;
import entities.ConsumidorEntity;
import entities.ImagenEntity;
import entities.ItemListaEntity;
import entities.ListaEntity;
import entities.LocalEntity;
import entities.ProductoEntity;
import entities.ProductoPrecioEntity;
import entities.UsuarioEntity;
import entities.VendedorEntity;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	static
	{
		try
		{
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.addAnnotatedClass(CadenaEntity.class);
			config.addAnnotatedClass(CategoriaEntity.class);
			config.addAnnotatedClass(ItemListaEntity.class);
			config.addAnnotatedClass(ListaEntity.class);
			config.addAnnotatedClass(LocalEntity.class);
			config.addAnnotatedClass(ProductoEntity.class);
			config.addAnnotatedClass(ProductoPrecioEntity.class);
			config.addAnnotatedClass(UsuarioEntity.class);
			config.addAnnotatedClass(ImagenEntity.class);
			config.addAnnotatedClass(VendedorEntity.class);
			config.addAnnotatedClass(ConsumidorEntity.class);
			
			sessionFactory = config.buildSessionFactory();
		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
