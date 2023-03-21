package com.latam.alura.tienda.modelo;

import javax.persistence.Entity;

@Entity
public class Libros extends Producto{

	private String autor;
	private int paginas;
	public Libros() {

	}
	public Libros(String autor, int paginas) {
		this.autor = autor;
		this.paginas = paginas;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
}
