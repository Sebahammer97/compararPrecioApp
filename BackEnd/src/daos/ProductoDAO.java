package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ProductoEntity;
import exceptions.ListaException;
import exceptions.ProductoException;
import hibernate.HibernateUtil;
import modelo.Categoria;
import modelo.Producto;

public class ProductoDAO {

	private static ProductoDAO instancia;
	
	private ProductoDAO() {}
	
	public static ProductoDAO getInstancia()
	{
		if(instancia==null)
			instancia = new ProductoDAO();
		return instancia;
	}
	
	public Producto getProducto(Integer id) throws ProductoException
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ProductoEntity producto = (ProductoEntity) s.createQuery("from ProductoEntity p where p.id = ?")
		.setInteger(0, id)
		.uniqueResult();
		if(producto != null)
			return toNegocio(producto);
		return null;
	}
	
	public ArrayList<Producto> getProductos() throws ProductoException
	{
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ProductoEntity> productos = s.createQuery("from ProductoEntity").list();
		for(ProductoEntity p : productos)
			resultado.add(toNegocio(p));
		return resultado;
	}

	public void saveLocal(Producto p) throws ListaException
	{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			ProductoEntity producto = new ProductoEntity(p.getId(), CategoriaDAO.getInstancia().toEntity(p.getCategoria()), p.getNombre(), p.getDescripcion());
			s.save(producto);
			s.getTransaction().commit();

			} catch (Exception e) {
				throw new ListaException("Producto Error -Fallo al guardar-");
			}
	}	
	
	public Producto toNegocio(ProductoEntity p) throws ProductoException
	{
		try {
			if(p != null) {
				Categoria categoria = CategoriaDAO.getInstancia().toNegocio(p.getCategoria());
				if(categoria != null) {
					return new Producto(p.getId(), p.getNombre(), p.getDescripcion(), categoria);
				}
			}
			
		} catch (Exception e) {
			throw new ProductoException("Producto Error -Fallo al transformar "+p.getId()+" a Negocio-");
		}
		return null;
	}
}
