package com.proyectoSisi.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name="ventas")
public class Venta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
    private String fechaYHora;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idClient")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_usuario")
	private Usuario usuarioVenta;
	

	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;
	
	 public Venta() {
	        this.fechaYHora = Utiles.obtenerFechaYHoraActual();
	    }

	public Venta(Integer id, String fechaYHora,
			Cliente cliente, Usuario usuarioVenta) {
		super();
		this.id = id;
		this.fechaYHora = fechaYHora;
		this.cliente = cliente;
		this.usuarioVenta = usuarioVenta;
	}
	
	

	public Venta(Cliente cliente, Usuario usuarioVenta) {
		super();
		this.cliente = cliente;
		this.usuarioVenta = usuarioVenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getTotal() {
		Double total = 0.0;
        for (ProductoVendido productoVendido : this.productos) {
            total += productoVendido.getTotal();
        }
        return total;
    }

	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuarioVenta() {
		return usuarioVenta;
	}

	public void setUsuarioVenta(Usuario usuarioVenta) {
		this.usuarioVenta = usuarioVenta;
	}


	public Set<ProductoVendido> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoVendido> productos) {
		this.productos = productos;
	}
	 
	
	 
	

	
	
}
