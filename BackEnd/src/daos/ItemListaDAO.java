package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import entities.ItemListaEntity;
import entities.ListaEntity;
import entities.ProductoEntity;
import exceptions.ItemListaException;
import hibernate.HibernateUtil;
import modelo.ItemLista;

public class ItemListaDAO
{
	private static ItemListaDAO instancia;

	private ItemListaDAO() {}

	public static ItemListaDAO getInstancia()
	{
		if(instancia==null)
			instancia = new ItemListaDAO();
		return instancia;
	}

	public void save(ItemLista i, ListaEntity le) throws ItemListaException{
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			ItemListaEntity ile = new ItemListaEntity(le, new ProductoEntity(i.getProducto().getId(), new CategoriaEntity(i.getProducto().getCategoria().getId(), i.getProducto().getCategoria().getTitulo(), i.getProducto().getCategoria().getDescripcion()) , i.getProducto().getNombre(), i.getProducto().getDescripcion()), i.getCantidad());
			s.save(ile);
			s.getTransaction().commit();
		} catch (Exception e) {
			throw new ItemListaException("No se pudo guardar el Item");
		}

	}

}
