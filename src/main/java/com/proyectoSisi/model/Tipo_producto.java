package com.proyectoSisi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "tipo_producto")
public class Tipo_producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoProducto")
	private Integer idTipoProducto;
	@Column(name = "nomTipoProducto", nullable = false, length = 50, unique = true)
	private String nomTipoProducto;

	@OneToMany(mappedBy="tipo_producto")
	private List<Categoria> IdTipoProduct;
	
	public Tipo_producto() {
		super();
	}

	public Tipo_producto(Integer idTipoProducto, String nomTipoProducto,
			List<Categoria> idTipoProduct) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.nomTipoProducto = nomTipoProducto;
		IdTipoProduct = idTipoProduct;
	}

	public Integer getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Integer idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNomTipoProducto() {
		return nomTipoProducto;
	}

	public void setNomTipoProducto(String nomTipoProducto) {
		this.nomTipoProducto = nomTipoProducto;
	}

	public List<Categoria> getIdTipoProduct() {
		return IdTipoProduct;
	}

	public void setIdTipoProduct(List<Categoria> idTipoProduct) {
		IdTipoProduct = idTipoProduct;
	}


	

	
}
