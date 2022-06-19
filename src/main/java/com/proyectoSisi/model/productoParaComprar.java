package com.proyectoSisi.model;



public class productoParaComprar extends Materia_prima{
		
	private static final long serialVersionUID = 1L;
	private Integer cantidad;
	private Double precio;
	
	public productoParaComprar(Integer idMateriaPrima, Integer codMateriaPrima, String nomMateriaPrima,
			Integer contenido_Product, String unidad_Medidad, String presentacion_Producto, Integer stock_Minimo,
			Integer stock_Actual, Categoria categoriaMP, Integer cantidad) {
		super(idMateriaPrima, codMateriaPrima, nomMateriaPrima, contenido_Product, unidad_Medidad,
				presentacion_Producto, stock_Minimo, stock_Actual, categoriaMP);
		this.cantidad = cantidad;
	}

	

	public productoParaComprar(Integer idMateriaPrima, Integer codMateriaPrima, String nomMateriaPrima,
			Integer stock_Actual, Integer cantidad, Double precio) {
		super(idMateriaPrima, codMateriaPrima, nomMateriaPrima, stock_Actual);
		this.cantidad = cantidad;
		this.precio = precio;
	}



	public productoParaComprar(Integer cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
	public void aumentarCantidad() {
		this.cantidad++;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	
	Detalle_compra detalle = new Detalle_compra();
	
	public Double getTotal() {
		return (double) (this.precio * this.cantidad);
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	
	
}
