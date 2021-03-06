package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ProductoPrecioEntity;
import exceptions.ProductoPrecioException;
import hibernate.HibernateUtil;
import modelo.Local;
import modelo.Producto;
import modelo.ProductoPrecio;

public class ProductoPrecioDAO {

	private static ProductoPrecioDAO instancia;
	
	private ProductoPrecioDAO() {}
	
	public static ProductoPrecioDAO getInstancia() {
		if(instancia==null)
			instancia = new ProductoPrecioDAO();
		return instancia;
	}
	
	public ArrayList<ProductoPrecio> getProductoPrecioByLocal(int idLocal) throws ProductoPrecioException {
		ArrayList<ProductoPrecio> resultado = new ArrayList<ProductoPrecio>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ProductoPrecioEntity> productosPrecios = s.createQuery("from ProductoPrecioEntity p where p.local = ?")
			.setInteger(0, idLocal)
			.list();
		for(ProductoPrecioEntity p : productosPrecios)
			resultado.add(toNegocio(p));
		return resultado;
	}

	public ProductoPrecio getProductoPrecioByLocalAndProducto(int idLocal, int idProducto) throws ProductoPrecioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ProductoPrecioEntity entidad = (ProductoPrecioEntity) s.createQuery("from ProductoPrecioEntity p where p.local = ? and p.producto = ?")
			.setInteger(0, idLocal)
			.setInteger(1, idProducto)
			.uniqueResult();
		ProductoPrecio resultado = toNegocio(entidad);
		return resultado;
	}
	
	public ProductoPrecio toNegocio(ProductoPrecioEntity p) throws ProductoPrecioException {
		try {
			if(p != null) {
				Local local = LocalDAO.getInstancia().toNegocio(p.getLocal());
				Producto producto = ProductoDAO.getInstancia().toNegocio(p.getProducto());
				if(local != null && producto != null) {
					return new ProductoPrecio(p.getId(), local, producto, p.getPrecio());
				}
			}
		} catch (Exception e) {
			throw new ProductoPrecioException("ProductoPrecio Error -Fallo al transformar "+p.getId()+" a Negocio-");
		}
		return null;
	}
}
