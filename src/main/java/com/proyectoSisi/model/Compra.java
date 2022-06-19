package com.proyectoSisi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity

@Table(name="compras")
public class Compra implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String fechaYHora;


	
	@ManyToOne
	@JoinColumn(name="Cod_Usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="Id_Proveedor")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy="compra")
	private List<Detalle_compra> Cod_Compra;
	


	 public Compra() {
	        this.fechaYHora = Utiles.obtenerFechaYHoraActual();
	  }


	public Compra(Integer id, String fechaYHora, Usuario usuario, Proveedor proveedor) {
		super();
		this.id = id;
		this.fechaYHora = fechaYHora;
		this.usuario = usuario;
		this.proveedor = proveedor;
	}


	public Compra(Usuario usuario, Proveedor proveedor) {
		super();
		this.usuario = usuario;
		this.proveedor = proveedor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public String getFechaYHora() {
		return fechaYHora;
	}


	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public List<Detalle_compra> getCod_Compra() {
		return Cod_Compra;
	}


	public void setCod_Compra(List<Detalle_compra> cod_Compra) {
		Cod_Compra = cod_Compra;
	}


	public Double getTotal() {
		Double total = 0.0;
        for (Detalle_compra detalleCompra : this.Cod_Compra) {
            total += detalleCompra.getTotal();
        }
        return total;
    }
	
	
	 
	 
	





}