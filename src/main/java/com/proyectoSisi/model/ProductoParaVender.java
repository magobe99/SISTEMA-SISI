package com.proyectoSisi.model;

public class ProductoParaVender extends Producto_procesado{
	
	private static final long serialVersionUID = 1L;
	private Integer cantidad;

	public ProductoParaVender(Integer idProduct, Integer codProduct, String nom_Product, Integer contenido_Product,
			String unidad_Medida, String presentacion_Producto, Double costoProduccion, Double valorUnitarioVenta,
			Integer stock_Minimo, Integer stock_Actual, String imagen, Categoria id_ProductePRO, Integer cantidad) {
		super(idProduct, codProduct, nom_Product, contenido_Product, unidad_Medida, presentacion_Producto,
				costoProduccion, valorUnitarioVenta, stock_Minimo, stock_Actual, imagen, id_ProductePRO);
		this.cantidad = cantidad;
	}

	
	
	public ProductoParaVender(String nom_Product, Integer codProduct, Double valorUnitarioVenta, Integer stock_Actual,
			Integer idProduct, Integer cantidad) {
		super(nom_Product, codProduct, valorUnitarioVenta, stock_Actual, idProduct);
		this.cantidad = cantidad;
	}

	

	public ProductoParaVender(Integer cantidad) {
		super();
		this.cantidad = cantidad;
	}



	public void aumentarCantidad() {
		this.cantidad++;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public Double getTotal() {
		return this.getValorUnitarioVenta() * this.cantidad;
	}
	
	
}
