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

@Table(name = "materias_primas")
public class Materia_prima implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMateriaPrima")
	private Integer IdMateriaPrima;
	@Column(name = "codMateriaPrima", nullable = false, length = 100, unique = true)
	private Integer codMateriaPrima;
	@Column(name = "nomMateriaPrima", nullable = false, length = 50)
	private String nomMateriaPrima;
	@Column(name = "contenido_Product", nullable = false, length = 10)
	private Integer contenido_Product;
	@Column(name = "unidad_Medidad", nullable = false, length = 30)
	private String unidad_Medidad;
	@Column(name = "presentacion_Producto", nullable = false, length = 40)
	private String presentacion_Producto;
	@Column(name = "stock_Minimo", nullable = false)
	private Integer stock_Minimo;
	@Column(name = "stock_Actual")
	private Integer stock_Actual;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name="IDcategoria")
	private Categoria categoriaMP;
	
	@OneToMany(mappedBy="materia_primas")
	private List<Existencia_mp> CodMateriaPrima;
	
	@OneToMany(mappedBy="materia_prima")
	private List<Detalle_compra> cod_MateriaPrima;
	
	
	@OneToMany(mappedBy="materia_primaProduccion")
	private List<Produccion> Cod_materiaPrima;
	
	public Materia_prima() {
		super();
	}

	public Materia_prima(Integer idMateriaPrima, Integer codMateriaPrima, String nomMateriaPrima,
			Integer contenido_Product, String unidad_Medidad, String presentacion_Producto, Integer stock_Minimo,
			Integer stock_Actual, Categoria categoriaMP) {
		super();
		IdMateriaPrima = idMateriaPrima;
		this.codMateriaPrima = codMateriaPrima;
		this.nomMateriaPrima = nomMateriaPrima;
		this.contenido_Product = contenido_Product;
		this.unidad_Medidad = unidad_Medidad;
		this.presentacion_Producto = presentacion_Producto;
		this.stock_Minimo = stock_Minimo;
		this.stock_Actual = stock_Actual;
		this.categoriaMP = categoriaMP;
	}
	
	

	public Materia_prima(Integer idMateriaPrima, Integer codMateriaPrima, String nomMateriaPrima,
			Integer stock_Actual) {
		super();
		IdMateriaPrima = idMateriaPrima;
		this.codMateriaPrima = codMateriaPrima;
		this.nomMateriaPrima = nomMateriaPrima;
		this.stock_Actual = stock_Actual;
	}

	public Materia_prima(Integer idMateriaPrima, Integer codMateriaPrima, String nomMateriaPrima,
			Integer contenido_Product, String unidad_Medidad, String presentacion_Producto, Integer stock_Minimo) {
		super();
		IdMateriaPrima = idMateriaPrima;
		this.codMateriaPrima = codMateriaPrima;
		this.nomMateriaPrima = nomMateriaPrima;
		this.contenido_Product = contenido_Product;
		this.unidad_Medidad = unidad_Medidad;
		this.presentacion_Producto = presentacion_Producto;
		this.stock_Minimo = stock_Minimo;
	}

	public Integer getIdMateriaPrima() {
		return IdMateriaPrima;
	}

	public void setIdMateriaPrima(Integer idMateriaPrima) {
		IdMateriaPrima = idMateriaPrima;
	}

	public Integer getCodMateriaPrima() {
		return codMateriaPrima;
	}

	public void setCodMateriaPrima(Integer codMateriaPrima) {
		this.codMateriaPrima = codMateriaPrima;
	}

	public String getNomMateriaPrima() {
		return nomMateriaPrima;
	}

	public void setNomMateriaPrima(String nomMateriaPrima) {
		this.nomMateriaPrima = nomMateriaPrima;
	}

	public Integer getContenido_Product() {
		return contenido_Product;
	}

	public void setContenido_Product(Integer contenido_Product) {
		this.contenido_Product = contenido_Product;
	}

	public String getUnidad_Medidad() {
		return unidad_Medidad;
	}

	public void setUnidad_Medidad(String unidad_Medidad) {
		this.unidad_Medidad = unidad_Medidad;
	}

	public String getPresentacion_Producto() {
		return presentacion_Producto;
	}

	public void setPresentacion_Producto(String presentacion_Producto) {
		this.presentacion_Producto = presentacion_Producto;
	}

	public Integer getStock_Minimo() {
		return stock_Minimo;
	}

	public void setStock_Minimo(Integer stock_Minimo) {
		this.stock_Minimo = stock_Minimo;
	}

	public Integer getStock_Actual() {
		return stock_Actual;
	}

	public void setStock_Actual(Integer stock_Actual) {
		this.stock_Actual = stock_Actual;
	}
	
	public void sumarExistencia(Integer stock_Actual) {
		this.stock_Actual += stock_Actual;
		
	}
	
	public void restarExistencia(Integer stock_Actual) {
		this.stock_Actual -= stock_Actual;
		
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoriaMP() {
		return categoriaMP;
	}

	public void setCategoriaMP(Categoria categoriaMP) {
		this.categoriaMP = categoriaMP;
	}
	

	
	
}
