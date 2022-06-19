package com.proyectoSisi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "detalle_compra")
public class Detalle_compra implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer cantidad;
	private Double precio;
	private String nombre;
	private Integer cod;

	
	
	@ManyToOne
	@JoinColumn(name = "cod_MateriaPrima")
	private Materia_prima materia_prima;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Compra")
	private Compra compra;
	
	public Detalle_compra() {
		super();
	}

	public Detalle_compra(Integer cantidad, Double precio, String nombre, Integer cod, Materia_prima materia_prima, Compra compra) {
		super();
		this.cantidad = cantidad;
		this.precio = precio;
		this.nombre = nombre;
		this.cod = cod;
		this.materia_prima = materia_prima;
		this.compra = compra;
	}
	
	public Double getTotal() {
        return this.cantidad * this.precio;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}


	public Materia_prima getMateria_prima() {
		return materia_prima;
	}

	public void setMateria_prima(Materia_prima materia_prima) {
		this.materia_prima = materia_prima;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public Double getSubTotal() {
		return this.cantidad * this.precio;
	}
   



	
}