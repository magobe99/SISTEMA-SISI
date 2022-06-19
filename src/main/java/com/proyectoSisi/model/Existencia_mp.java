package com.proyectoSisi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
@Table(name="existencias_mp")
public class Existencia_mp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_exisMP")
	private Integer cod_exisMP;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha", nullable = false)
	private Timestamp fecha;
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	
	@ManyToOne
	@JoinColumn(name="CodMateriaPrima")
	private Materia_prima materia_primas;
	
	public Existencia_mp() {
		super();
	}

	public Existencia_mp(Integer cod_exisMP, Timestamp fecha, Integer cantidad, Materia_prima materia_primas) {
		super();
		this.cod_exisMP = cod_exisMP;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.materia_primas = materia_primas;
	}

	public Integer getCod_exisMP() {
		return cod_exisMP;
	}

	public void setCod_exisMP(Integer cod_exisMP) {
		this.cod_exisMP = cod_exisMP;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Materia_prima getMateria_primas() {
		return materia_primas;
	}

	public void setMateria_primas(Materia_prima materia_primas) {
		this.materia_primas = materia_primas;
	}

	
	
	
}
