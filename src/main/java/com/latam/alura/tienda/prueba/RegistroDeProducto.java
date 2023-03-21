package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.CategoriaId;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
	    ProductoDao productoDao = new ProductoDao(em);
	    
	    Producto producto = productoDao.consultaPorId(1l);
	    System.out.println(producto.getNombre());
	    
	    BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
	    System.out.println(precio);
	    
	    Categoria find = em.find(Categoria.class, new CategoriaId("CELULARES","456"));
	    
	    System.out.println(find.getNombre());

	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");

		Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"), celulares);

	    EntityManager em = JPAUtils.getEntityManager();
	    ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        
	    em.getTransaction().begin();
	    
	    categoriaDao.guardar(celulares);
	    productoDao.guardar(celular);	
	    
	    em.getTransaction().commit();
	    em.close();
	}

}



