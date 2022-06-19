package com.proyectoSisi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "categorias")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Categoria")
	private Integer id_Categoria;
	@Column(name = "nombreCategoria", nullable = false, length = 50)
	private String nombreCategoria;

	
	@ManyToOne
	@JoinColumn(name="IdTipoProduct")
	private Tipo_producto tipo_producto;
	
	@OneToMany(mappedBy="categoriaMP")
	private List<Materia_prima> IDcategoria;
	
	@OneToMany(mappedBy="id_ProductePRO")
	private List<Producto_procesado> Id_Category;
	
	public Categoria() {
		super();
	}

	public Categoria(Integer id_Categoria, String nombreCategoria, Tipo_producto tipo_producto) {
		super();
		this.id_Categoria = id_Categoria;
		this.nombreCategoria = nombreCategoria;
		this.tipo_producto = tipo_producto;
	}

	public Integer getId_Categoria() {
		return id_Categoria;
	}

	public void setId_Categoria(Integer id_Categoria) {
		this.id_Categoria = id_Categoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Tipo_producto getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(Tipo_producto tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	

	


	
}
