package com.latam.alura.tienda.prueba;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;


public class LoadRecords {
	public static void cargarRegistros() throws FileNotFoundException {
		EntityManager em = JPAUtils.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProductoDao productoDao = new ProductoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		em.getTransaction().begin();
		
		loadCategoria("categoria",categoriaDao,em);
		
		loadProducto("producto",productoDao,categoriaDao,em);
		
		loadCliente("cliente",clienteDao,em);
		
		List<Cliente> clientesList = clienteDao.consultarTodos();
		List<Pedido> pedidoList= new ArrayList<>();
		
		for(Cliente cl:clientesList) {
			pedidoList.add(new Pedido(cl));
		}
		
		for(int i=0;i<pedidoList.size();i++) {
			pedidoList.get(i).agregarItems(new ItemsPedido(i+1,productoDao.consultaPorId((long) (i+1)),pedidoList.get(i)));
			pedidoDao.guardar(pedidoList.get(i));
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void loadProducto(String type, ProductoDao productoDao,CategoriaDao categoriaDao, EntityManager em) throws FileNotFoundException {
		List<String> productosTxt =readFile(type);
		for(int i=0;i<productosTxt.size();i++) {
			String[] line = productosTxt.get(i).split(";");
			if(line.length>1) {
				Categoria categoria=categoriaDao.consultaPorNombre(line[3]);
				Producto producto = new Producto(line[4],line[0],new BigDecimal(line[1]),categoria);
				productoDao.guardar(producto);
				em.flush();
			}
		}
	}

	private static void loadCategoria(String type, CategoriaDao categoriaDao,EntityManager em) throws FileNotFoundException {
		List<String> categoriasTxt =readFile(type);		
		for(int i=0;i<categoriasTxt.size();i++) {
			String[] line = categoriasTxt.get(i).split(";");
			if(line.length==1) {
				Categoria categoria = new Categoria(categoriasTxt.get(i));
				categoriaDao.guardar(categoria);
				em.flush();	
			}
		}
	}

	private static void loadCliente(String type, ClienteDao clienteDao,EntityManager em) throws FileNotFoundException {
		List<String> clientesTxt =readFile(type);		
		for(int i=0;i<clientesTxt.size();i++) {
			String[] line = clientesTxt.get(i).split("~");
			System.out.println(line[0]+line[1]);
			if(line.length>1) {
				Cliente cliente= new Cliente(line[0],line[1]);
				clienteDao.guardar(cliente);
				em.flush();	
			}
		}
	}
	
	private static List<String> readFile(String type) throws FileNotFoundException {
		File file = new File("C:\\Users\\Public\\Alura\\jpa\\"+type+".txt");
		Scanner scan = new Scanner(file);
		List<String> pedido= new ArrayList<>();
		while(scan.hasNextLine()){
			pedido.add(scan.nextLine());
		}
		scan.close();
		return pedido;
	}

	
}
