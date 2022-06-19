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

@Table(name = "Producto_procesado")
public class Producto_procesado implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProduct")
	private Integer IdProduct;
	@Column(name = "codProduct", nullable = false, length = 100, unique = true)
	private Integer codProduct;
	@Column(name = "nom_Product", nullable = false, length = 50)
	private String nom_Product;
	@Column(name = "contenido_Product", nullable = false, length = 10)
	private Integer contenido_Product;
	@Column(name = "unidad_Medida", nullable = false, length = 30)
	private String unidad_Medida;
	@Column(name = "presentacion_Producto", nullable = false, length = 40)
	private String presentacion_Producto;	
	@Column(name = "costoProduccion", nullable = false)
	private Double costoProduccion;
	@Column(name = "valorUnitarioVenta", nullable = false)
	private Double valorUnitarioVenta;
	@Column(name = "stock_Minimo", nullable = false)
	private Integer stock_Minimo;
	@Column(name = "stock_Actual")
	private Integer stock_Actual;
	
	private String imagen;

	
	@ManyToOne
	@JoinColumn(name="Id_Category")
	private Categoria id_ProductePRO;
	
	@OneToMany(mappedBy="codigo")
	private List<ProductoVendido> id_product;
	
	@OneToMany(mappedBy="product_process")
	private List<Existencia_pp> Cod_Producte;
	
	@OneToMany(mappedBy="Prod_procesado")
	private List<Lote> Cod_Producto;
	

	
	public Producto_procesado() {
		super();
	}

	public Producto_procesado(Integer idProduct, Integer codProduct, String nom_Product, Integer contenido_Product,
			String unidad_Medida, String presentacion_Producto, Double costoProduccion, Double valorUnitarioVenta,
			Integer stock_Minimo, Integer stock_Actual, String imagen, Categoria id_ProductePRO) {
		super();
		IdProduct = idProduct;
		this.codProduct = codProduct;
		this.nom_Product = nom_Product;
		this.contenido_Product = contenido_Product;
		this.unidad_Medida = unidad_Medida;
		this.presentacion_Producto = presentacion_Producto;
		this.costoProduccion = costoProduccion;
		this.valorUnitarioVenta = valorUnitarioVenta;
		this.stock_Minimo = stock_Minimo;
		this.stock_Actual = stock_Actual;
		this.imagen = imagen;
		this.id_ProductePRO = id_ProductePRO;
	}
	
	public Producto_procesado(String nom_Product, Integer codProduct, Double valorUnitarioVenta, Integer stock_Actual, Integer idProduct) {
		super();
		this.nom_Product = nom_Product;
		this.codProduct = codProduct;
		this.valorUnitarioVenta = valorUnitarioVenta;
		this.stock_Actual = stock_Actual;
		IdProduct = idProduct;
	}
	
	

	
	public Producto_procesado(Integer idProduct, Integer codProduct, String nom_Product, Integer contenido_Product,
			String unidad_Medida, String presentacion_Producto, Double costoProduccion, Double valorUnitarioVenta,
			Integer stock_Minimo) {
		super();
		IdProduct = idProduct;
		this.codProduct = codProduct;
		this.nom_Product = nom_Product;
		this.contenido_Product = contenido_Product;
		this.unidad_Medida = unidad_Medida;
		this.presentacion_Producto = presentacion_Producto;
		this.costoProduccion = costoProduccion;
		this.valorUnitarioVenta = valorUnitarioVenta;
		this.stock_Minimo = stock_Minimo;
	}

	public Integer getIdProduct() {
		return IdProduct;
	}

	public void setIdProduct(Integer idProduct) {
		IdProduct = idProduct;
	}

	public Integer getCodProduct() {
		return codProduct;
	}

	public void setCodProduct(Integer codProduct) {
		this.codProduct = codProduct;
	}

	public String getNom_Product() {
		return nom_Product;
	}

	public void setNom_Product(String nom_Product) {
		this.nom_Product = nom_Product;
	}

	public Integer getContenido_Product() {
		return contenido_Product;
	}

	public void setContenido_Product(Integer contenido_Product) {
		this.contenido_Product = contenido_Product;
	}

	public String getUnidad_Medida() {
		return unidad_Medida;
	}

	public void setUnidad_Medida(String unidad_Medida) {
		this.unidad_Medida = unidad_Medida;
	}

	public String getPresentacion_Producto() {
		return presentacion_Producto;
	}

	public void setPresentacion_Producto(String presentacion_Producto) {
		this.presentacion_Producto = presentacion_Producto;
	}

	public Double getCostoProduccion() {
		return costoProduccion;
	}

	public void setCostoProduccion(Double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public Double getValorUnitarioVenta() {
		return valorUnitarioVenta;
	}

	public void setValorUnitarioVenta(Double valorUnitarioVenta) {
		this.valorUnitarioVenta = valorUnitarioVenta;
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
	
	public void restarExistencia(Integer stock_Actual) {
		this.stock_Actual -= stock_Actual;
		
	}
	
	public void sumarExistencia(Integer stock_Actual) {
		this.stock_Actual += stock_Actual;
		
	}
	public boolean sinExistencia() {
        return this.stock_Actual <= 0;
    }
	
	public boolean noStock(Integer stock_Actual) {
        return this.stock_Actual < stock_Actual;
    }
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getId_ProductePRO() {
		return id_ProductePRO;
	}

	public void setId_ProductePRO(Categoria id_ProductePRO) {
		this.id_ProductePRO = id_ProductePRO;
	}

	



	
	


	
	
}
