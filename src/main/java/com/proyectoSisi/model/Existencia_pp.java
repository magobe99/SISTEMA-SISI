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
@Table(name = "existencias_pp")

public class Existencia_pp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_ExisPP")
	private Integer cod_ExisPP;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Producte")
	private Producto_procesado product_process;
	
	public Existencia_pp() {
		super();
	}

	public Existencia_pp(Integer cod_ExisPP, Date fecha, Integer cantidad, Producto_procesado product_process) {
		super();
		this.cod_ExisPP = cod_ExisPP;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.product_process = product_process;
	}

	public Integer getCod_ExisPP() {
		return cod_ExisPP;
	}

	public void setCod_ExisPP(Integer cod_ExisPP) {
		this.cod_ExisPP = cod_ExisPP;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto_procesado getProduct_process() {
		return product_process;
	}

	public void setProduct_process(Producto_procesado product_process) {
		this.product_process = product_process;
	}

	
	
}
