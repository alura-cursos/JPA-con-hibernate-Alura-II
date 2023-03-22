package com.latam.alura.tienda.prueba;

import java.io.FileNotFoundException;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.utils.JPAUtils;

public class PruebaDeDesempenho {
	public static void main(String[] args) throws FileNotFoundException {
//		LoadRecords.cargarRegistros();
		EntityManager em = JPAUtils.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2l);
		
		em.close();
		
//		System.out.println(pedido.getFecha());
//		System.out.println(pedido.getItems().size());
		System.out.println(pedidoConCliente.getCliente().getNombre());
	}
}
