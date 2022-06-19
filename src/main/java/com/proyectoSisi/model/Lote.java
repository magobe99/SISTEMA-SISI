package com.proyectoSisi.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;


@Entity
@Table(name ="lotes")
public class Lote implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLotes;
	private String fechaIngreso;
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaLote;
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimiento;
	private Integer cantidad;

	
	@ManyToOne
	@JoinColumn(name="Id_usuario")
	private Usuario userLote;
	
	@ManyToOne
	@JoinColumn(name="Cod_Producto")
	private Producto_procesado Prod_procesado;
	
	public Lote() {
		this.fechaIngreso = Utiles.obtenerFechaYHoraActual();
	}

	

	public Lote(Integer idLotes, String fechaIngreso, LocalDate fechaLote, LocalDate fechaVencimiento,
			Integer cantidad, Usuario userLote, Producto_procesado prod_procesado) {
		super();
		this.idLotes = idLotes;
		this.fechaIngreso = fechaIngreso;
		this.fechaLote = fechaLote;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidad = cantidad;
		this.userLote = userLote;
		Prod_procesado = prod_procesado;
	}



	public Lote(String fechaIngreso, LocalDate fechaLote, LocalDate fechaVencimiento, Integer cantidad,
			Usuario userLote, Producto_procesado prod_procesado) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaLote = fechaLote;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidad = cantidad;
		this.userLote = userLote;
		Prod_procesado = prod_procesado;
	}



	public Integer getIdLotes() {
		return idLotes;
	}

	public void setIdLotes(Integer idLotes) {
		this.idLotes = idLotes;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaLote() {
		return fechaLote;
	}

	public void setFechaLote(LocalDate fechaLote) {
		this.fechaLote = fechaLote;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getUserLote() {
		return userLote;
	}

	public void setUserLote(Usuario userLote) {
		this.userLote = userLote;
	}

	public Producto_procesado getProd_procesado() {
		return Prod_procesado;
	}

	public void setProd_procesado(Producto_procesado prod_procesado) {
		Prod_procesado = prod_procesado;
	}


	

	
	
}
