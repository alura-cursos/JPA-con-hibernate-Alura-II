package com.latam.alura.tienda.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DatosPersonales implements Serializable{


	private static final long serialVersionUID = 8063180201812979106L;
	
	private String nombre;
	private String dni;

	public DatosPersonales() {

	}
	
	
	public DatosPersonales(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
}
