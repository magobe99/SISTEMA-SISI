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
@Table(name = "detalle_venta")
public class ProductoVendido implements Serializable {
	
		
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		private Integer cantidad;
		private Double precio;
		private String nombre;
		private Integer cod;
		
		@ManyToOne
		@JoinColumn(name = "id_product")
		private Producto_procesado codigo;
		
		@ManyToOne
		@JoinColumn(name = "id_Ventas")
		private Venta venta;

		

		public ProductoVendido(Integer cantidad, Double precio, String nombre, Integer cod,
				Producto_procesado codigo, Venta venta) {
			super();
			this.cantidad = cantidad;
			this.precio = precio;
			this.nombre = nombre;
			this.cod = cod;
			this.codigo = codigo;
			this.venta = venta;
		}
		

		public ProductoVendido() {
	    }

	    public Double getTotal() {
	        return this.cantidad * this.precio;
	    }

	    public Venta getVenta() {
	        return venta;
	    }

	    public void setVenta(Venta venta) {
	        this.venta = venta;
	    }

	    public Double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(Double precio) {
	        this.precio = precio;
	    }

	    public Integer getCantidad() {
	        return cantidad;
	    }

	    public void setCantidad(Integer cantidad) {
	        this.cantidad = cantidad;
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

		public Producto_procesado getCodigo() {
			return codigo;
		}

		public void setCodigo(Producto_procesado codigo) {
			this.codigo = codigo;
		}
		
		public Double getSubTotal() {
			return this.cantidad * this.precio;
		}
	   
		
		
		
		
		
		
}
