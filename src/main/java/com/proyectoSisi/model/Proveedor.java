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
@Table(name="proveedores")
public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "doc_Proveedor", nullable = false, length = 30, unique = true)
	private String doc_Proveedor;
	@Column(name = "nomEmpresa", nullable = false, length = 50)
	private String nomEmpresa;
	@Column(name = "tipo_documento", nullable = false, length = 50)
	private String tipo_documento;
	@Column(name = "nomRepresentante", nullable = false, length = 50)
	private String nomRepresentante;
	@Column(name = "apellidoRepresentante", nullable = false, length = 50)
	private String apellidoRepresentante;
	@Column(name = "correo", nullable = false, length = 50)
	private String correo;
	@Column(name = "telefono", nullable = false, length = 20)
	private String telefono;
	@Column(name = "direccion", nullable = false, length = 60)
	private String direccion;

	
	@OneToMany(mappedBy="proveedor")
	private List<Compra> Id_Proveedor;
	
	public Proveedor() {
		super();
	}

	public Proveedor(Integer id, String doc_Proveedor, String nomEmpresa, String tipo_documento,
			String nomRepresentante, String apellidoRepresentante, String correo, String telefono, String direccion) {
		super();
		this.id = id;
		this.doc_Proveedor = doc_Proveedor;
		this.nomEmpresa = nomEmpresa;
		this.tipo_documento = tipo_documento;
		this.nomRepresentante = nomRepresentante;
		this.apellidoRepresentante = apellidoRepresentante;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDoc_Proveedor() {
		return doc_Proveedor;
	}

	public void setDoc_Proveedor(String doc_Proveedor) {
		this.doc_Proveedor = doc_Proveedor;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNomRepresentante() {
		return nomRepresentante;
	}

	public void setNomRepresentante(String nomRepresentante) {
		this.nomRepresentante = nomRepresentante;
	}

	public String getApellidoRepresentante() {
		return apellidoRepresentante;
	}

	public void setApellidoRepresentante(String apellidoRepresentante) {
		this.apellidoRepresentante = apellidoRepresentante;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return doc_Proveedor + " - " + nomEmpresa;
	}

	

	

	


}
