package com.proyectoSisi.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity

@Table(name = "producciones")
public class Produccion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducciones;
	private String fechaSalida;
	private Integer cantidad;

	
	@ManyToOne
	@JoinColumn(name="Cod_materiaPrima")
	private Materia_prima materia_primaProduccion;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuarioProduccion;
	
	public Produccion() {
		this.fechaSalida=Utiles.obtenerFechaYHoraActual();
	}

	public Produccion(Integer idProducciones, String fechaSalida, Integer cantidad, Materia_prima materia_primaProduccion,
			Usuario usuarioProduccion) {
		super();
		this.idProducciones = idProducciones;
		this.fechaSalida = fechaSalida;
		this.cantidad = cantidad;
		this.materia_primaProduccion = materia_primaProduccion;
		this.usuarioProduccion = usuarioProduccion;
	}
	
	

	public Produccion(String fechaSalida, Integer cantidad, Materia_prima materia_primaProduccion,
			Usuario usuarioProduccion) {
		super();
		this.fechaSalida = fechaSalida;
		this.cantidad = cantidad;
		this.materia_primaProduccion = materia_primaProduccion;
		this.usuarioProduccion = usuarioProduccion;
	}

	public Integer getIdProducciones() {
		return idProducciones;
	}

	public void setIdProducciones(Integer idProducciones) {
		this.idProducciones = idProducciones;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Materia_prima getMateria_primaProduccion() {
		return materia_primaProduccion;
	}

	public void setMateria_primaProduccion(Materia_prima materia_primaProduccion) {
		this.materia_primaProduccion = materia_primaProduccion;
	}

	public Usuario getUsuarioProduccion() {
		return usuarioProduccion;
	}

	public void setUsuarioProduccion(Usuario usuarioProduccion) {
		this.usuarioProduccion = usuarioProduccion;
	}

	
	
}
